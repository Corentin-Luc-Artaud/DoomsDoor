package Vanargand.DoomsDoor.utils;

import Vanargand.DoomsDoor.ramCleaner.RamCleaner;
import Vanargand.DoomsDoor.ramCleaner.sessionCleaner;
import Vanargand.DoomsDoor.uriHandler.DefaultHandler;
import Vanargand.DoomsDoor.uriHandler.URIHandlerList;

public class DefaultLoader implements Loader {

	@Override
	public void load() {
		RamCleaner.getInstance().addCleaner(new sessionCleaner());
    	URIHandlerList.getInstance().getHandlerList().put("/etat/*", new DefaultHandler());
	}

}
