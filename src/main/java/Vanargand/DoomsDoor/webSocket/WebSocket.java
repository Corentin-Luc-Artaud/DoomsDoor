package Vanargand.DoomsDoor.webSocket;

import java.net.Socket;

import Vanargand.DoomsDoor.socket.httpSocket;

public class WebSocket {
	private Socket socket;
	
	public WebSocket(httpSocket socket) {
		this.socket = socket.getSocket();
	}
	
	public WebSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void writeMessage(String message){
		
	}
	
	public String recieveMessage() {
		return null;
	}

}

