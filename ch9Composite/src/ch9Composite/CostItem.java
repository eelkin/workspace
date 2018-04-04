package ch9Composite;

public class CostItem extends CarComponent{

	private String name;
	private double price;
	
	public CostItem(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	@Override
	public void print() {
		// TODO Auto-generated method stub
		//System.out.println(name + "    $" + price);
		System.out.printf( "%-40s %40s %n", name, "$"+price);
	}

	

}
