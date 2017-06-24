package Vanargand.DoomsDoor.web.utils;

import Vanargand.DoomsDoor.web.ramCleaner.RamCleaner;
import Vanargand.DoomsDoor.web.ramCleaner.sessionCleaner;
import Vanargand.DoomsDoor.web.uriHandler.DefaultHandler;
import Vanargand.DoomsDoor.web.uriHandler.URIHandlerList;

public class DefaultLoader implements Loader {

	@Override
	public void load() {
		RamCleaner.getInstance().addCleaner(new sessionCleaner());
    	URIHandlerList.getInstance().getHandlerList().put("/etat/*", new DefaultHandler());
	}

}
