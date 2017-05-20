package Vanargand.DoomsDoor.ramCleaner;

import Vanargand.DoomsDoor.session.SessionStore;

public class sessionCleaner implements Cleaner{

	@Override
	public void clean() {
		SessionStore.getInstance().clean();
	}
	
}
