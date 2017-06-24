package Vanargand.DoomsDoor.web.eventStream;

import Vanargand.DoomsDoor.web.session.Session;

public interface EventStreamHandler {
	public void loop(HttpEvent event, Session session);
	public String getContentType();
}
