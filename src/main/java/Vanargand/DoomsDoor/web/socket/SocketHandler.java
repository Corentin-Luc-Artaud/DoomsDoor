package Vanargand.DoomsDoor.web.socket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Clock;
import java.util.Base64;

import Vanargand.DoomsDoor.web.http.Cookie;
import Vanargand.DoomsDoor.web.http.HttpRequest;
import Vanargand.DoomsDoor.web.http.HttpResponse;
import Vanargand.DoomsDoor.web.session.Session;
import Vanargand.DoomsDoor.web.session.SessionStore;
import Vanargand.DoomsDoor.web.uriHandler.URIHandler;
import Vanargand.DoomsDoor.web.uriHandler.URIHandlerList;
import Vanargand.DoomsDoor.web.utils.Config;

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
		URIHandler handler = null;
		if ((key = URIHandlerList.getInstance().resolve(request.getRequest())) != null) {
			handler = URIHandlerList.getInstance().getHandlerList().get(key);
			handler.handler(request, response, session);
		} else {
			response.setStatus("404", "Not Found");
		}
		response.setVersion("HTTP/1.1");
		String serverName = Config.getInstance().get("ServerName");
		response.setServer(serverName != "0" ? serverName :"Doom's Door");
		//response.setConnection("Close");
		response.addCookie(sessionCookie);
		socket.writeResponse(response);
		if (handler != null) handler.afterSend(socket, session);
		if (socket != null) socket.close();
	}
}
