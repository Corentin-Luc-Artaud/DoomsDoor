package Vanargand.DoomsDoor.web.session;

import java.time.LocalDateTime;
import java.util.TreeSet;

import Vanargand.DoomsDoor.web.http.Cookie;

public class Session implements Comparable<Session>{
	
	private String id;
	private TreeSet<Cookie> cookies;
	private LocalDateTime lastUse;
	
	public Session(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void addCookie(Cookie cookie) {
		cookies.add(cookie);
	}
	
	public Cookie getCookie(String key) {
		for (Cookie cookie : cookies) {
			if (cookie.getKey().equals(key)) {
				return cookie;
			}
		}
		return null;
	}
	
	public void removeCookie(String key) {
		for (Cookie cookie : cookies) {
			if (cookie.getKey().equals(key)) {
				cookies.remove(cookie);
				break;
			}
		}
	}
	
	public void setLastUsetoNow() {
		this.lastUse = LocalDateTime.now();
	}
	
	public LocalDateTime getLastUse() {
		return lastUse;
	}

	@Override
	public int compareTo(Session session) {
		return id.compareTo(session.id);
	}

}
