package ch9Composite;

public abstract class CarComponent {
	
	public void add(CarComponent cc) {
		throw new UnsupportedOperationException();
	}
	public void remove(CarComponent cc) {
		throw new UnsupportedOperationException();
	}
	public CarComponent getChild(int i) {
		throw new UnsupportedOperationException();
	};
	
	public String getName() {;
		throw new UnsupportedOperationException();
	}
	/*
	public String getDescription() {
		throw new UnsupportedOperationException();
	};
	*/
	public double getPrice() {
		throw new UnsupportedOperationException();
	};
	
	public void print() {
		throw new UnsupportedOperationException();
	};
}
