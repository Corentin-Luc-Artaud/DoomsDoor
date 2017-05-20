package Vanargand.DoomsDoor.server;

import java.io.IOException;
import java.net.ServerSocket;

import Vanargand.DoomsDoor.socket.HttpStandardSocket;
import Vanargand.DoomsDoor.socket.SocketHandler;

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
