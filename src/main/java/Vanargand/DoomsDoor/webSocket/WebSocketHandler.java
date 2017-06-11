package Vanargand.DoomsDoor.webSocket;

import Vanargand.DoomsDoor.session.Session;
import Vanargand.DoomsDoor.socket.httpSocket;

public interface WebSocketHandler {
	public void handle(httpSocket socket, Session session);
}
