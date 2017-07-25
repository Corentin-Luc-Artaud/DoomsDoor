package Vanargand.DoomsDoor.web.eventStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class StreamRepository extends ArrayList<Stream>{
	private static final long serialVersionUID = -8903784800428461602L;
	private static StreamRepository instance = new StreamRepository();
	
	Lock lock;
	
	private StreamRepository(){
		lock = new ReentrantLock();
	}
	
	public static StreamRepository getInstance() {
		return instance;
	}
	
	@Override
	public Iterator<Stream> iterator() {
		lock.lock();
		return super.iterator();
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		lock.lock();
		boolean res = super.removeAll(c);
		lock.unlock();
		return res;
	}
	@Override
	public void add(int arg0, Stream arg1) {
		lock.lock();
		super.add(arg0, arg1);
		lock.unlock();
	}
	
	public void unlock() {
		lock.unlock();
	}
}
