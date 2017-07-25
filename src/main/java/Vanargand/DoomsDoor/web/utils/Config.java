package Vanargand.DoomsDoor.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Config extends HashMap<String, String>{
	private static final long serialVersionUID = -7352461297668463796L;
	private static final String config = "/Config.cfg";
	private static Config instance = new Config();
	private static Lock lock = new ReentrantLock();
	
	private Config() {
		
	}
	public static Config getInstance() {
		return instance;
	}
	
	public static void configure() throws IOException {
		if(! lock.tryLock()) return;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
		Config.class.getResourceAsStream(config)));
		String line;
		System.out.println("Config:");
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			if (line.startsWith("//")) continue;
			String [] keyValue = line.split(" = ", 2);
			if (keyValue.length != 2) continue;
			instance.put(keyValue[0], keyValue[1]);
		}
	}
	
	@Override
	public String get(Object arg0) {
		String res = super.get(arg0);
		if (res == null || res.length() == 0) res = "0";
		return res;
	}
}
