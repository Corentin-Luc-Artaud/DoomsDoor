package Vanargand.DoomsDoor.uriHandler;

import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;
import Vanargand.DoomsDoor.session.Session;

public interface Handler {
	public void handler(HttpRequest request, HttpResponse response, Session session);
}
