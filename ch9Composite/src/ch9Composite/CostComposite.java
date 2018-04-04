package ch9Composite;

import java.util.ArrayList;
import java.util.Iterator;

public class CostComposite extends CarComponent{

	private ArrayList<CarComponent> components = new ArrayList<CarComponent>();
	String name;
	double price;
	
	public CostComposite(String name) {
		this.name = name;
		price = getPrice();
	}
	
	public void add(CarComponent cc) {
		components.add(cc);
	}
	
	public void remove(CarComponent cc) {
		components.remove(cc);
	}
	
	public CarComponent getChild(int i) {
		return components.get(i);
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		double price = 0;
		
		Iterator<CarComponent> iterator = components.iterator();
		while(iterator.hasNext()) {
			CarComponent cc = iterator.next();
			price += cc.getPrice();
		}
		return price;
	}
	
	public void print() {
		System.out.println(getName());
		
		Iterator<CarComponent> iterator = components.iterator();
		while(iterator.hasNext()) {
			CarComponent cc = iterator.next();
			cc.print();
		}
	}
	
}
