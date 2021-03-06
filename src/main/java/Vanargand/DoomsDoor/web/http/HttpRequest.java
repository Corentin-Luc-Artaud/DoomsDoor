package Vanargand.DoomsDoor.web.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	private String webSocketKey;
	private String WebSocketVersion;
	private String upgrade;
	
	

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
				case "sec-webSocket-key":
					this.webSocketKey = line[1];
					break;
				case "Sec-WebSocket-Version":
					this.WebSocketVersion = line[1];
					break;
				case "Upgrade":
					this.upgrade = line[1];
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
	
	public String getWebSocketKey() {
		return webSocketKey;
	}

	public String getWebSocketVersion() {
		return WebSocketVersion;
	}

	public String getUpgrade() {
		return upgrade;
	}
	
	public String getPath() {
		if (this.method.toLowerCase().equals("get")){
			return request.split("?")[0];
		} else if(this.method.toLowerCase().equals("post")){
			return request;
		}
		return request;
	}
	
	public Map<String, String> getParameters() {
		Map<String, String> res = new HashMap<String, String>();
		String params [] = new String[0];
		if (this.method.toLowerCase().equals("get")){
			if (this.request.contains("?")){
				String parts [] = this.request.split("\\?");
				if (parts.length > 1) {
					String paramsList = parts[1];
					params = paramsList.split("&");
				}
			}
		} else if(this.method.toLowerCase().equals("post")){
			params = this.content.split("&");
		}
		for (int i = 0; i < params.length; ++i) {
			String[] keyValue = params[i].split("=");
			if(keyValue.length > 1)
				res.put(keyValue[0], keyValue[1]);
		}
		return res;
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
				+ (WebSocketVersion != null && WebSocketVersion.length() > 0 ? "\r\nSec-WebSocket-Version:"+WebSocketVersion : "")
				+ (webSocketKey != null && webSocketKey.length() > 0 ? "\r\nSec-WebSocket-Key:"+webSocketKey : "")
				+ (upgrade != null && upgrade.length() > 0 ? "\r\nSec-WebSocket-Key:"+upgrade : "")
				+"\r\n\r\n"+(content != null ? content : "");
	}
	
	
}
