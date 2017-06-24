package Vanargand.DoomsDoor.web.ramCleaner;

import Vanargand.DoomsDoor.web.session.SessionStore;

public class sessionCleaner implements Cleaner{

	@Override
	public void clean() {
		SessionStore.getInstance().clean();
	}
	
}
