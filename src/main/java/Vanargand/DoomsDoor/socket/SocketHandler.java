package Vanargand.DoomsDoor.socket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.Base64;

import Vanargand.DoomsDoor.http.Cookie;
import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;
import Vanargand.DoomsDoor.session.Session;
import Vanargand.DoomsDoor.session.SessionStore;
import Vanargand.DoomsDoor.uriHandler.HandlerList;
import Vanargand.DoomsDoor.utils.Config;

public class SocketHandler implements Runnable{
	private httpSocket socket;
	
	public SocketHandler(httpSocket socket) {
		this.socket = socket;
	}
	
	private Session createSession(HttpResponse response) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String id = new String(socket.getIp()+Clock.systemUTC().millis());
		Session session = new Session(
				Base64.getEncoder().encodeToString(digest.digest(id.getBytes())).replaceAll("=", ""));
		SessionStore.getInstance().addSession(session);
		return session;
	}
	
	private Session setSession(HttpRequest request, HttpResponse response, Cookie sessionCookie) {
		Cookie ssid = null;
		Session session = null;
		if ((ssid = request.getCookie(Config.getInstance().get("sessionToken"))) != null) {
			session = SessionStore.getInstance().getSession(ssid.getValue());
			if (session == null) {
				session = createSession(response);
			}
		} else {
			System.err.println("pas de cookie de session");
			session = createSession(response);
		}
		sessionCookie.setKey(Config.getInstance().get("sessionToken"));
		sessionCookie.setValue(session.getId());
		sessionCookie.setPath("/");
		session.setLastUsetoNow();
		return session;
	}
	
	
	public void run() {
		HttpRequest request = socket.readRequest();
		if (request == null) {
			socket.close();
			return;
		}
		HttpResponse response = new HttpResponse();
		Cookie sessionCookie = new Cookie();
		Session session = setSession(request, response, sessionCookie);
		String key;
		if ((key = HandlerList.getInstance().resolve(request.getRequest())) != null) {
			HandlerList.getInstance().getHandlerList().get(key).handler(request, response, session);
		} else {
			response.setStatus("404", "Not Found");
		}
		response.setVersion("HTTP/1.1");
		response.setServer("Doom's Door");
		response.setConnection("Close");
		response.addCookie(sessionCookie);
		socket.writeResponse(response);
		socket.close();
	}
}
