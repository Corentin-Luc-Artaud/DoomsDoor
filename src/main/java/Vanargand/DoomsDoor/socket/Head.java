package Vanargand.DoomsDoor.socket;

public class Head {
	private String head;
	
	public Head() {
		head = new String();
	}
	
	public void headNewLine(String line) {
		if (line == null) return;
		head += line+"\r\n";
	}
	
	public String getHead() {
		return head;
	}
	
	@Override
	public String toString() {
		return head;
	}
}
