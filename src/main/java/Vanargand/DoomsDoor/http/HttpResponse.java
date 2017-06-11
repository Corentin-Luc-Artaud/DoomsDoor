package Vanargand.DoomsDoor.http;

import java.util.ArrayList;

public class HttpResponse {
	private String version;
	private String statusCode;
	private String statusText;
	private String date;
	private String server;
	private String connection;
	private String contentType;
	private ArrayList<Cookie> cookies;
	private String range;
	private int contentLength;
	private String expires;
	private String lastModify;
	private String content;
	private String location;
	private String upgrade;
	private String webSocketAccept;
	
	
	public void setLocation(String location) {
		this.location = location;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public HttpResponse() {
		cookies = new ArrayList<Cookie>();
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setStatus(String statusCode, String statusText) {
		this.statusCode = statusCode;
		this.statusText = statusText;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	public void setCookies(ArrayList<Cookie> cookies) {
		this.cookies = cookies;
	}

	public void setRange(int startpos, int endpos) {
		this.range = "bytes "+startpos+"-"+endpos+"/"+(endpos-startpos);
	}
	
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}
	
	public String cookieList() {
		String res = new String();
		int i = 0;
		for (Cookie cookie : cookies) {
			res += (i++ == 0 ? "" : "\r\n")+"Set-Cookie: "+cookie.toString();
		}
		return res;
	}

	public void setUpgrade(String upgrade) {
		this.upgrade = upgrade;
	}

	public void setWebSocketAccept(String webSocketAccept) {
		this.webSocketAccept = webSocketAccept;
	}

	@Override
	public String toString() {
		return version + " " + statusCode + " " + statusText
				+ (location != null && location.length() >0 ? "Location: "+location : "")
				+ (date != null &&date.length() > 0 ? "\r\nDate: " + date : "")
				+ (server != null && server.length() > 0 ? "\r\nServer: " + server : "")
				+ (connection != null && connection.length() > 0 ? "\r\nConnection: "+connection : "")
				+ (contentType != null && contentType.length() > 0 ? "\r\nContent-Type: " + contentType : "") 
				+ (cookies.size() > 0 ? "\r\n"+cookieList() : "")
				+ (range != null && range.length() > 0 ? "\r\nContent-Range: " + range : "")
				+ (contentLength > 0 ? "\r\nContent-Length: " + contentLength : "")
				+ (expires != null && expires.length() > 0 ? "\r\n Expires: " + expires : "")
				+ (lastModify != null && lastModify.length() > 0 ? "\r\nLast-Modified: " + lastModify : "")
				+ (upgrade != null && upgrade.length() > 0 ? "\r\nUpgrade: " + upgrade : "")
				+ (webSocketAccept != null && webSocketAccept.length() > 0 ? "\r\nSec-WebSocket-Accept: " + webSocketAccept : "")
				+"\r\n\r\n"+(content != null ? content : "");
	}
}
