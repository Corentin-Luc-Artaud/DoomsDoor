package Vanargand.DoomsDoor.web.socket;

import java.net.Socket;

import Vanargand.DoomsDoor.web.http.HttpRequest;
import Vanargand.DoomsDoor.web.http.HttpResponse;

public interface httpSocket {
	public HttpRequest readRequest ();
	public void writeResponse (HttpResponse response);
	public void close();
	public String getIp();
	public Socket getSocket();
}
