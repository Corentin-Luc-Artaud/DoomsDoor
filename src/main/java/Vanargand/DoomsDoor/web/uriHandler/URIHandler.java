package Vanargand.DoomsDoor.web.uriHandler;

import Vanargand.DoomsDoor.web.http.HttpRequest;
import Vanargand.DoomsDoor.web.http.HttpResponse;
import Vanargand.DoomsDoor.web.session.Session;
import Vanargand.DoomsDoor.web.socket.httpSocket;

public interface URIHandler {
	public void handler(HttpRequest request, HttpResponse response, Session session);
	public void afterSend(httpSocket socket, Session session);
}
