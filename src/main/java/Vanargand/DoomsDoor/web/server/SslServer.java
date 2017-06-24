package Vanargand.DoomsDoor.web.server;

import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

import Vanargand.DoomsDoor.web.socket.HttpStandardSocket;
import Vanargand.DoomsDoor.web.socket.SocketHandler;

public class SslServer implements Runnable{
	
	private int port;
	private boolean alive;
	
	private static Lock lock = new ReentrantLock();
	
	public static  void loadKeystore(String path, String password) {
		lock.lock(); // only once		
		System.setProperty("javax.net.ssl.keyStore", path);
		System.setProperty("javax.net.ssl.keyStorePassword", password);
	}
	
	public SslServer(int port) {
		this.port = port;
		alive = true;
	}

	@Override
	public void run() {
		try {
			SSLServerSocket server = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
			while (alive) {
				SocketHandler handler = new SocketHandler(new HttpStandardSocket(server.accept()));
				ThreadPool.getInstance().execute(handler);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
