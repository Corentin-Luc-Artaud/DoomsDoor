package Vanargand.DoomsDoor.uriHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandlerList {
	private Map<String, Handler> handlerList;
	private static HandlerList instance = new HandlerList();
	
	private HandlerList() {
		handlerList = new HashMap<String, Handler>();
	}
	
	public Map<String, Handler> getHandlerList() {
		return handlerList;
	}
	public static HandlerList getInstance() {
		return instance;
	}
	
	public String resolve(String path) {
		for (String key : handlerList.keySet()) {
			Pattern pattern = Pattern.compile(key);
			Matcher matcher = pattern.matcher(path);
			if (matcher.find()) return key;
		}
		return null;
	}
}
