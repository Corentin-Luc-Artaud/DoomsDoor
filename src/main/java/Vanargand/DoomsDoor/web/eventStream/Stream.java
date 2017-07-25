package Vanargand.DoomsDoor.web.eventStream;

import Vanargand.DoomsDoor.web.session.Session;
import Vanargand.DoomsDoor.web.socket.httpSocket;

public class Stream {
	private Session session;
	private httpSocket socket;
	
	public Stream(Session session, httpSocket socket) {
		this.session = session;
		this.socket = socket;
	}
	
	public Session getSession() {
		return session;
	}
	
	public httpSocket getSocket() {
		return socket;
	}
}
