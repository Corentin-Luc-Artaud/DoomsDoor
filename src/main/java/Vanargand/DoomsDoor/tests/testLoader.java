package Vanargand.DoomsDoor.tests;

import javax.xml.ws.handler.HandlerResolver;

import Vanargand.DoomsDoor.eventStream.EventStream;
import Vanargand.DoomsDoor.uriHandler.URIHandlerList;
import Vanargand.DoomsDoor.utils.DefaultLoader;
import Vanargand.DoomsDoor.utils.Loader;

public class testLoader implements Loader {

	@Override
	public void load() {
		DefaultLoader loader = new DefaultLoader();
		loader.load();
		
		EventStream evtstr = new EventStream(new MessageRandom());
		URIHandlerList.getInstance().getHandlerList().put("/sse/", evtstr);
		URIHandlerList.getInstance().getHandlerList().put("/index/", new DefaultHandler());

	}

}
