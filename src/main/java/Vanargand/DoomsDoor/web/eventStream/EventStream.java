package Vanargand.DoomsDoor.web.eventStream;

import java.io.IOException;

import Vanargand.DoomsDoor.web.http.HttpRequest;
import Vanargand.DoomsDoor.web.http.HttpResponse;
import Vanargand.DoomsDoor.web.session.Session;
import Vanargand.DoomsDoor.web.socket.httpSocket;
import Vanargand.DoomsDoor.web.uriHandler.URIHandler;

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
