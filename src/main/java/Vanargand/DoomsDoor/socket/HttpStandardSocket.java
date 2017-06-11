package Vanargand.DoomsDoor.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import Vanargand.DoomsDoor.http.HttpRequest;
import Vanargand.DoomsDoor.http.HttpResponse;

public class HttpStandardSocket implements httpSocket{
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	public HttpStandardSocket(Socket socket) throws IOException {
		this.socket = socket;
		this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	private Head readHead() {
		Head res = new Head();
		String line = new String();
		try {
			while (((line = reader.readLine()) != null) && !line.isEmpty()){
				res.headNewLine(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return res;
	}

	@Override
	public HttpRequest readRequest() {
		Head request = readHead();
		HttpRequest res;
		try {
			res = new HttpRequest(request.getHead());
		} catch (Exception e1) {
			return null;
		}
		if (res.getContentLength() > 0 ) {
			String data = new String();
			try {
				for (int i = 0; i < res.getContentLength(); ++i) {
						data += ""+reader.read();
				}
			} catch (IOException e) {
				e.printStackTrace();
				return res;
			}
			res.setContent(data);
		}
		System.out.println();
		return res;
	}

	@Override
	public void writeResponse(HttpResponse response) {
		try {
			writer.write(response.toString());
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getIp() {
		return socket.getInetAddress().getHostAddress();
	}

	@Override
	public Socket getSocket() {
		return this.socket;
	}
}
