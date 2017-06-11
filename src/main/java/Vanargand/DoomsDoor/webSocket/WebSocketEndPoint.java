package Vanargand.DoomsDoor.webSocket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import Vanargand.DoomsDoor.http.Cookie;
import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;
import Vanargand.DoomsDoor.session.Session;
import Vanargand.DoomsDoor.socket.httpSocket;
import Vanargand.DoomsDoor.uriHandler.URIHandler;

public class WebSocketEndPoint implements URIHandler{
	private WebSocketHandler handler;
	
	public WebSocketEndPoint(WebSocketHandler handler) {
		this.handler = handler;
	}

	@Override
	public void handler(HttpRequest request, HttpResponse response, Session session) {
		if (request.getConnection().toLowerCase() != "upgrade" || request.getWebSocketVersion() != "13") {
			response.setStatus("400", "Bad Request");
			Cookie cookie = new Cookie("isWs", "0");
			session.addCookie(cookie);
		}
		String key = request.getWebSocketKey()+"258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		key = Base64.getEncoder().encodeToString(md.digest(key.getBytes()));
		response.setWebSocketAccept(key);
		response.setStatus("101", "Switching Protocols");
		response.setUpgrade("WebSocket");
		response.setConnection("Upgrade");	
		Cookie cookie = new Cookie("isWs", "1");
		session.addCookie(cookie);
	}

	@Override
	public void afterSend(httpSocket socket, Session session) {
		if (Integer.parseInt(session.getCookie("isWs").getValue()) == 0) return;
		handler.handle(socket, session);
		socket.close();
	}
	
}
