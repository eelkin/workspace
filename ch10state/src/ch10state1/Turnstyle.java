package ch10state1;

public class Turnstyle {
	
	enum State {LOCKED, UNLOCKED};
	public State state;
	
	public Turnstyle() {
		state = State.LOCKED;
	}
	
	public void insertCoin() {
		if(state == State.UNLOCKED) {
			System.out.println("thank you");
		} else if (state == State.LOCKED) {
			state = State.UNLOCKED;
		}
	}
	
	public void pass() {
		if(state == State.UNLOCKED) {
			state = State.LOCKED;
		} else if (state == State.LOCKED) {
			System.out.println("*alarm blaring*");
		}
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return state;
	}
}
