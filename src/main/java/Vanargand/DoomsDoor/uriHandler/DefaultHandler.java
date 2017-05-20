package Vanargand.DoomsDoor.uriHandler;

import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;
import Vanargand.DoomsDoor.session.Session;

public class DefaultHandler implements Handler {

	@Override
	public void handler(HttpRequest request, HttpResponse response, Session session) {
		response.setStatus("200", "OK");
		response.setContentType("text/html");
		response.setContent("Server Ok");
	}

}
