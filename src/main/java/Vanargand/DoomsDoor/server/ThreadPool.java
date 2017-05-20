package Vanargand.DoomsDoor.server;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPool {
	private Executor executor;
	private static Lock lock = new ReentrantLock();
	
	private static ThreadPool instance;
	
	private ThreadPool(int nbThreads) {
		this.executor = Executors.newFixedThreadPool(nbThreads);
	}
	
	public static ThreadPool getInstance() {
		return instance;
	}
	
	public void execute(Runnable runnable) {
		this.executor.execute(runnable);
	}
	
	public static void init(int nbThreads) {
		if (! lock.tryLock()) return;
		instance = new ThreadPool(nbThreads);
	}
}
