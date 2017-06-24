package Vanargand.DoomsDoor.tests;


import Vanargand.DoomsDoor.web.eventStream.EventStream;
import Vanargand.DoomsDoor.web.uriHandler.URIHandlerList;
import Vanargand.DoomsDoor.web.utils.DefaultLoader;
import Vanargand.DoomsDoor.web.utils.Loader;

public class testLoader implements Loader {

	@Override
	public void load() {
		DefaultLoader loader = new DefaultLoader();
		loader.load();
		
		EventStream evtstr = new EventStream(new MessageRandom());
		URIHandlerList.getInstance().getHandlerList().put("/sse/", evtstr);
		URIHandlerList.getInstance().getHandlerList().put("/", new DefaultHandler());

	}

}
