package ch10state2;

public class UnlockedState implements State{

	Turnstyle ts;
	
	public UnlockedState(Turnstyle ts) {
		this.ts = ts;
	}
	@Override
	public void insertCoin() {
		// TODO Auto-generated method stub
		System.out.println("thank you");
	}

	@Override
	public void pass() {
		// TODO Auto-generated method stub
		ts.setState(ts.getLockedState());
	}

	public String toString() {
		return "unlocked";
	}
}
