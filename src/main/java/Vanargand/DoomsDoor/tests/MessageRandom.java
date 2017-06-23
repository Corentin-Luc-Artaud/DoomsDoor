package Vanargand.DoomsDoor.tests;

import java.util.Random;

import Vanargand.DoomsDoor.eventStream.EventStreamHandler;
import Vanargand.DoomsDoor.eventStream.HttpEvent;
import Vanargand.DoomsDoor.session.Session;

public class MessageRandom implements EventStreamHandler{

	@Override
	public void loop(HttpEvent event, Session session) {
		Random rand = new Random(System.nanoTime());
		event.setData(""+rand.nextLong());;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getContentType() {
		return "text/event-stream";
	}

}
