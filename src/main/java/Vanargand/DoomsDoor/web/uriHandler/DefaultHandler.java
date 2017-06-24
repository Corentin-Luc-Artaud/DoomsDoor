package Vanargand.DoomsDoor.web.uriHandler;

import Vanargand.DoomsDoor.web.http.HttpRequest;
import Vanargand.DoomsDoor.web.http.HttpResponse;
import Vanargand.DoomsDoor.web.session.Session;
import Vanargand.DoomsDoor.web.socket.httpSocket;

public class DefaultHandler implements URIHandler {

	@Override
	public void handler(HttpRequest request, HttpResponse response, Session session) {
		response.setStatus("200", "OK");
		response.setContentType("text/html");
		response.setContent("Server Ok");
	}

	@Override
	public void afterSend(httpSocket socket, Session session) {
		socket.close();
		return;
	}

}
