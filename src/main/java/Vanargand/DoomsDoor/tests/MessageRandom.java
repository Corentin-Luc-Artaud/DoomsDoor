package Vanargand.DoomsDoor.tests;

import java.util.Random;

import Vanargand.DoomsDoor.web.eventStream.EventStreamHandler;
import Vanargand.DoomsDoor.web.eventStream.HttpEvent;
import Vanargand.DoomsDoor.web.session.Session;

public class MessageRandom implements EventStreamHandler{

	@Override
	public void loop(HttpEvent event, Session session) {
		Random rand = new Random(System.nanoTime());
		event.setData(""+rand.nextLong());;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getContentType() {
		return "text/event-stream";
	}

}
