package Vanargand.DoomsDoor.web.eventStream;

public class HttpEvent {
	private String event;
	private String data;
	private String id;
	
	public HttpEvent() {
		this("", "", "");
	}
	public HttpEvent(String event, String data, String id) {
		this.event = event;
		this.data = data;
		this.id = id;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public void setData(String data) {
		this.data = data;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		String res = new String();
		res += id != null && id.length() > 0 ? "id: "+id+"\r\n" : "";
		res += event != null && event.length() > 0 ? "event: "+event+"\r\n" : "";
		res += data != null && data.length() > 0 ? "data: "+data+"\r\n" : "";
		res += res.length() > 0 ? "\r\n" : "";
		return res;
	}
}
