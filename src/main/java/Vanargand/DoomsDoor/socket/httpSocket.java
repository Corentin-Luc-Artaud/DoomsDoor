package Vanargand.DoomsDoor.socket;

import java.net.Socket;

import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;

public interface httpSocket {
	public HttpRequest readRequest ();
	public void writeResponse (HttpResponse response);
	public void close();
	public String getIp();
	public Socket getSocket();
}
