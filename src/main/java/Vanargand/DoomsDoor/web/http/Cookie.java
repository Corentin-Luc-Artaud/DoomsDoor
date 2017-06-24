package Vanargand.DoomsDoor.web.http;

public class Cookie implements Comparable<Cookie>{
	private String key;
	private String value;
	private String expires;
	private String path;
	private String command;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Cookie(String key, String value){
		this.key = key;
		this.value = value;
	}
	
	public Cookie() {
		this("","");
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setExpires(String expires) {
		this.expires = expires;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getExpires() {
		return expires;
	}

	@Override
	public String toString() {
		return key+"="+value
				+(expires != null && expires.length() > 0 ? "; Expires"+expires : "")
				+(path != null && path.length() > 0 ? "; Path="+path : "")
				+(command != null && command.length() > 0 ? "; "+command : "");
	}
	
	public boolean isValid() {
		return value.length()>0 && key.length()>0;
	}

	@Override
	public int compareTo(Cookie cookie) {
		return this.key.compareTo(cookie.key);
	}
}
