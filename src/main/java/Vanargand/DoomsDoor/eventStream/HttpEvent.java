package Vanargand.DoomsDoor.eventStream;

public class HttpEvent {
	private String event;
	private String data;
	private String id;
	
	public HttpEvent() {
		this("", "");
	}
	public HttpEvent(String event, String data) {
		this.event = event;
		this.data = data;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		String res = new String();
		res += id.length() > 0 ? "id: "+id+"\r\n" : "";
		res += event.length() > 0 ? "event: "+event+"\r\n" : "";
		res += data.length() > 0 ? "data: "+data+"\r\n" : "";
		res += res.length() > 0 ? "\r\n" : "";
		return res;
	}
}
