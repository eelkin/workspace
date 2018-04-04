package ch9Composite;

public class CarBuyer {
	CarComponent allCarComponents;
	
	public CarBuyer(CarComponent allCarComponents) {
		this.allCarComponents = allCarComponents;
	}
	
	public void printComponents() {
		allCarComponents.print();
		//System.out.println("Total MSRP as built " + allCarComponents.getPrice());
		System.out.printf( "%-40s %40s %n", "", "Total MSRP as built $" + allCarComponents.getPrice()+"");
	}
	

}
