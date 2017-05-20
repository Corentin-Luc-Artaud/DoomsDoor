package Vanargand.DoomsDoor.ramCleaner;


import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Vanargand.DoomsDoor.utils.Config;

public class RamCleaner implements Runnable{
	public static final long wait = 1200000; //20 minutes
	private ArrayList<Cleaner> cleaners;
	private Lock lock;
	private boolean alive;
	
	private static RamCleaner instance = new RamCleaner();
	
	private RamCleaner() {
		cleaners = new ArrayList<Cleaner>();
		alive = false;
		lock = new ReentrantLock();
	}
	
	public void addCleaner(Cleaner cleaner) {
		if(! lock.tryLock() ) return;
		cleaners.add(cleaner);
		lock.unlock();
	}
	
	public void removeCleaner(Cleaner cleaner) {
		if(! lock.tryLock() ) return;
		cleaners.remove(cleaner);
		lock.unlock();
	}
	
	public void kill() {
		alive = false;
	}

	@Override
	public void run() {
		while(! lock.tryLock() );
		alive = true;
		while (alive) {
			try {
				Thread.sleep(Long.parseLong(Config.getInstance().get("CleanerInterval")));
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			for (int i = 0; i < cleaners.size() && alive; ++i) {
				cleaners.get(i).clean();
			}
		}
		lock.unlock();
	}
	
	public static RamCleaner getInstance() {
		return instance;
	}

}
