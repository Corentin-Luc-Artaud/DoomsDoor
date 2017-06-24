package Vanargand.DoomsDoor.web.session;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Vanargand.DoomsDoor.web.utils.Config;

public class SessionStore {
	private TreeSet<Session> sessions;
	private Lock lock;
	private static SessionStore instance = new SessionStore();
	
	private SessionStore() {
		sessions = new TreeSet<Session>();
		lock = new ReentrantLock();
	}
	
	public void addSession(Session session) {
		while (! lock.tryLock());
		sessions.add(session);
		lock.unlock();
	}
	
	public void removeSession(String id) {
		while (! lock.tryLock());
		for (Session session : sessions) {
			if (session.getId() == id) {
				sessions.remove(session);
				break;
			}
		}
		lock.unlock();
	}
	
	public Session getSession(String id) {
		while (! lock.tryLock());
		for (Session session : sessions) {
			if (session.getId().equals(id)) {
				lock.unlock();
				return session;
			}
		}
		lock.unlock();
		return null;
	}
	
	public static SessionStore getInstance() {
		return instance;
	}

	
	public void clean() {
		while (! lock.tryLock());
		ArrayList<Session> toRemoves = new ArrayList<Session>();
		LocalDateTime now = LocalDateTime.now();
		now = now.minusMinutes(Long.parseLong(Config.getInstance().get("SessionLiveTime")));
		for (Session session : sessions) {
			if (session.getLastUse() == null || session.getLastUse().isBefore(now)) {
				toRemoves.add(session);
			}
		}
		sessions.removeAll(toRemoves);
		lock.unlock();
	}
}