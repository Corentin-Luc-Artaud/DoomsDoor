package Vanargand.DoomsDoor.eventStream;

import java.io.IOException;

import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;
import Vanargand.DoomsDoor.session.Session;
import Vanargand.DoomsDoor.socket.httpSocket;
import Vanargand.DoomsDoor.uriHandler.URIHandler;

public class EventStream implements URIHandler{
	private EventStreamHandler handler;
	
	public EventStream(EventStreamHandler handler) {
		this.handler = handler;
	}
	
	@Override
	public void handler(HttpRequest request, HttpResponse response, Session session) {
		response.setContentType(handler.getContentType());
	}

	@Override
	public void afterSend(httpSocket socket, Session session) {
		
		while (! socket.getSocket().isClosed()) {
			HttpEvent event = new HttpEvent();
			handler.loop(event, session);
			try {
				System.out.println(event.toString());
				socket.getSocket().getOutputStream().write(event.toString().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		
	}
	
}
