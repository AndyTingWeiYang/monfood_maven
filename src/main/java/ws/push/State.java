package ws.push;

import java.util.Set;

public class State {
	private String stateType;
	// the res changing the state
	private String id;
	// total allres
	private Set<String> allUser;
	public State() {
		super();
		// TODO Auto-generated constructor stub
	}
	public State(String stateType, String id, Set<String> allUser) {
		super();
		this.stateType = stateType;
		this.id = id;
		this.allUser = allUser;
	}
	public State(String stateType, String id) {
		this.stateType = stateType;
		this.id = id;
	}
	public String getStateType() {
		return stateType;
	}
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<String> getAllUser() {
		return allUser;
	}
	public void setAllUser(Set<String> allUser) {
		this.allUser = allUser;
	}
	
	
	
	
	
	
}
