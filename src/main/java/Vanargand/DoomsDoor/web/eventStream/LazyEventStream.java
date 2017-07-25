package Vanargand.DoomsDoor.web.eventStream;

import java.io.IOException;

import Vanargand.DoomsDoor.web.session.Session;
import Vanargand.DoomsDoor.web.socket.HttpStandardSocket;
import Vanargand.DoomsDoor.web.socket.httpSocket;

public class LazyEventStream extends EventStream{ 
	public LazyEventStream(EventStreamHandler handler) {
		super(handler);
	}
	
	@Override
	public void afterSend(httpSocket socket, Session session) {
		try {
			HttpStandardSocket socket2 = new HttpStandardSocket(socket.getSocket());
			Stream stream = new Stream(session, socket2);
			StreamRepository.getInstance().add(stream);
			socket = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
