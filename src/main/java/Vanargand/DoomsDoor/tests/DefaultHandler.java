package Vanargand.DoomsDoor.tests;

import Vanargand.DoomsDoor.web.http.HttpRequest;
import Vanargand.DoomsDoor.web.http.HttpResponse;
import Vanargand.DoomsDoor.web.session.Session;
import Vanargand.DoomsDoor.web.socket.httpSocket;
import Vanargand.DoomsDoor.web.uriHandler.URIHandler;

public class DefaultHandler implements URIHandler{
	private static final String Html = "<!DOCTYPE html><html><head></head><body>\r\n"
			+ "<div id=\"test\"></div>"
			+ "	<script>"
			+ "evtsrc = new EventSource( \"/sse/\" );"
			+ "test = document.getElementById('test');"
			+ "evtsrc.onmessage = function(e) {"
			+ "test.append(e.data+'<br />')"
			+ "}"
			+ "</script>"
			+ "\r\n</body>";

	@Override
	public void handler(HttpRequest request, HttpResponse response, Session session) {
		response.setStatus("200", "Ok");
		response.setContentType("text/html");
		response.setContent(Html);
		response.setContentLength(Html.length());
	}

	@Override
	public void afterSend(httpSocket socket, Session session) {
		return;
	}

}
