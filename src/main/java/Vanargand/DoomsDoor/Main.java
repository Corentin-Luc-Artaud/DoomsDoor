package Vanargand.DoomsDoor;

import java.io.IOException;

import Vanargand.DoomsDoor.utils.DefaultLoader;
import Vanargand.DoomsDoor.utils.Starter;

public class Main {
	public static void main (String [] args) throws Exception {
		try {
			Starter.start(new DefaultLoader());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
