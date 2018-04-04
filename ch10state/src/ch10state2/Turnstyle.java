package ch10state2;

public class Turnstyle {
	
	State unlocked;
	State locked;
	State state;
	
	public Turnstyle() {
		unlocked = new UnlockedState(this);
		locked = new LockedState(this);
		
		state = locked;
	}
	
	public void insertCoin() {
		state.insertCoin();
	}
	
	public void pass() {
		state.pass();
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getLockedState() {
		return locked;
	}
	
	public State getUnlockedState() {
		return unlocked;
	}
	
	public String toString() {
		return state.toString();
	}
}
