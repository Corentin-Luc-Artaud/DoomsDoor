package Vanargand.DoomsDoor.webSocket;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;
import Vanargand.DoomsDoor.session.Session;

public class HandChecker {
	public static boolean handCheck(HttpRequest request, HttpResponse response, Session session) throws NoSuchAlgorithmException{
		if (request.getConnection().toLowerCase() != "upgrade" || request.getWebSocketVersion() != "13") {
			response.setStatus("400", "Bad Request");
			return false;
		}
		String key = request.getWebSocketKey()+"258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		key = Base64.getEncoder().encodeToString(md.digest(key.getBytes()));
		response.setWebSocketAccept(key);
		response.setStatus("101", "Switching Protocols");
		response.setUpgrade("WebSocket");
		response.setConnection("Upgrade");
		return true;
	}
}
