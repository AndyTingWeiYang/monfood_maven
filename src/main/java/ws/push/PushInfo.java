package ws.push;

public class PushInfo {

	private String type;
	private String sender;
	private String receiver;
	private String message;
	public PushInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PushInfo(String type, String sender, String receiver, String message) {
		super();
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	
	
	
}
