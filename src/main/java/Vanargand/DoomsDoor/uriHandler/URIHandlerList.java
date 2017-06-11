package Vanargand.DoomsDoor.uriHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URIHandlerList {
	private Map<String, URIHandler> handlerList;
	private static URIHandlerList instance = new URIHandlerList();
	
	private URIHandlerList() {
		handlerList = new HashMap<String, URIHandler>();
	}
	
	public Map<String, URIHandler> getHandlerList() {
		return handlerList;
	}
	public static URIHandlerList getInstance() {
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
