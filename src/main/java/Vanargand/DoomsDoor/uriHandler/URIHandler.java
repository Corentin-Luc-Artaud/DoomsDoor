package Vanargand.DoomsDoor.uriHandler;

import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;
import Vanargand.DoomsDoor.session.Session;
import Vanargand.DoomsDoor.socket.httpSocket;

public interface URIHandler {
	public void handler(HttpRequest request, HttpResponse response, Session session);
	public void afterSend(httpSocket socket, Session session);
}
