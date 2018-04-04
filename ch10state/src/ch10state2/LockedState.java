package ch10state2;

public class LockedState implements State{

	Turnstyle ts;
	
	public LockedState(Turnstyle ts) {
		this.ts = ts;
	}
	@Override
	public void insertCoin() {
		// TODO Auto-generated method stub
		ts.setState(ts.getUnlockedState());
	}

	@Override
	public void pass() {
		// TODO Auto-generated method stub
		System.out.println("sounding the alarm");
	}
	
	public String toString() {
		return "locked";
	}

}
