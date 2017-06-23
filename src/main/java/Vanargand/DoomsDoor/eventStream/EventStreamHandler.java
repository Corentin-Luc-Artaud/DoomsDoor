package Vanargand.DoomsDoor.eventStream;

import Vanargand.DoomsDoor.session.Session;

public interface EventStreamHandler {
	public void loop(HttpEvent event, Session session);
	public String getContentType();
}
