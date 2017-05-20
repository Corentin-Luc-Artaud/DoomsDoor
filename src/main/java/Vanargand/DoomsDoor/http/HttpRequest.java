package Vanargand.DoomsDoor.http;

import java.util.ArrayList;

public class HttpRequest {
	private String method;
	private String request;
	private String version;
	private String host;
	private String referer;
	private String userAgent;
	private ArrayList<Cookie> cookies;
	private String range;
	private int contentLength;
	private String connection;
	private String content;
	private String ip;
	
	

	public HttpRequest(String request) throws Exception {
		cookies = new ArrayList<Cookie>();
		String [] head = request.split("\r\n");
		for (int i = 0; i < head.length; ++i) {
			
			if (i == 0) {
				String [] command = head[i].split(" ", 3);
				if (command.length != 3) throw new Exception("Unknow command");
				this.method = command[0];
				this.request = command[1];
				this.version = command[2];
			} else {
				String [] line = head[i].split(": ", 2);
				switch (line[0].toLowerCase()) {
				case "host":
					this.host = line[1];
					break;
				case "referer":
					this.referer = line[1];
					break;
				case "user-agent":
					this.userAgent = line[1];
					break;
				case "content-length":
					this.contentLength = Integer.parseInt(line[1]);
					break;
				case "cookie":
					loadCookies(line[1]);
					break;
				case "range":
					this.range = line[1];
					break;
				case "connection" :
					this.connection = line[1];
					break;
				default:
					break;
				}
			}
			
		}
	}
	
	private void loadCookies(String cookies) {
		ArrayList<Cookie> res = new ArrayList<Cookie>();
		String [] cookielist = cookies.split(";");
		for (int i = 0; i < cookielist.length; ++i) {
			Cookie cookie = new Cookie();
			cookie.setKey(cookielist[i].split("=")[0]);
			cookie.setValue(cookielist[i].split("=")[1]);
			res.add(cookie);
		}
		this.cookies.addAll(res);
	}
	
	private String CookiesList () {
		String res = new String();
		for (Cookie cookie : cookies) {
			res += cookie.toString();
		}
		return res;
	}
	
	public ArrayList<Cookie> getCookies() {
		return cookies;
	}
	
	public Cookie getCookie(String key) {
		for (Cookie cookie : cookies) {
			if (cookie.getKey().equals(key)) {
				return cookie;
			}
		}
		return null;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getContent() {
		return content;
	}
	
	public int getContentLength() {
		return contentLength;
	}
	
	public String getMethod() {
		return method;
	}

	public String getRequest() {
		return request;
	}

	public String getVersion() {
		return version;
	}

	public String getHost() {
		return host;
	}

	public String getReferer() {
		return referer;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public String getRange() {
		return range;
	}
	
	public String getConnection() {
		return connection;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int[] range() {
		if (range.length() <= 0 || ! range.startsWith("bytes")) return null;
		String [] util = (range.split(" ")[1]).split("-");
		int [] res = new int[2];
		res[0] = Integer.parseInt(util[0]);
		if (util.length == 2)
			res[1] = Integer.parseInt(util[1]);
		return res;
	}

	@Override
	public String toString() {
		return method + " " + request + " " + version 
				+ (host != null && host.length() > 0 ? "\r\n"+"Host: " + host : "")
				+ (referer != null && referer.length() > 0 ? "\r\n"+"Referer: " + referer : "") 
				+ (userAgent != null && userAgent.length() > 0 ? "\r\n"+"User-Agent:" + userAgent : "")
				+ (cookies.size() > 0 ? "\r\nCookie: "+CookiesList(): "")
				+ (contentLength > 0 ? "\r\nContent-Length: "+contentLength : "")
				+ (connection != null && connection.length() > 0 ? "\r\nConnection: "+connection : "" )
				+"\r\n\r\n"+(content != null ? content : "");
	}
	
	
}
