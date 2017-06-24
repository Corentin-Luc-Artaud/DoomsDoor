package Vanargand.DoomsDoor.web.server;

import java.io.IOException;
import java.net.ServerSocket;

import Vanargand.DoomsDoor.web.socket.HttpStandardSocket;
import Vanargand.DoomsDoor.web.socket.SocketHandler;

public class StandardServer implements Runnable{
	private int port;
	private boolean alive;
	
	public StandardServer(int port) {
		this.port = port;
		alive = true;
	}
	
	public void run() {
		try {
			ServerSocket server = new ServerSocket(port);
			while (alive) {
				SocketHandler handler = new SocketHandler(new HttpStandardSocket(server.accept()));
				ThreadPool.getInstance().execute(handler);
			}
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
