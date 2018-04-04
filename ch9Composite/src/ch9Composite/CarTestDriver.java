package ch9Composite;

public class CarTestDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CarComponent model = new CostComposite("Model");
		CarComponent colors = new CostComposite("Colors");
		CarComponent packages = new CostComposite("Packages");
		CarComponent accessories = new CostComposite("Accessories");
		
		CarComponent allComponents = new CostComposite("Summary");
		
		allComponents.add(model);
		allComponents.add(colors);
		allComponents.add(packages);
		
		model.add(new CostItem("Camry Hybrid SE", 29500));
		
		colors.add(new CostItem("Exterior: Ruby Flair Pearl", 0));
		colors.add(new CostItem("Interior: Black SoftTex", 0));
		
		packages.add(accessories);
		packages.add(new CostItem("Destination, Process and Handling Fee", 895));
		
		accessories.add(new CostItem("Blind Spot Monitor", 600));
		accessories.add(new CostItem("Carpet Mats", 299));
		
		CarBuyer cb = new CarBuyer(allComponents);
		
		cb.printComponents();
	}

}
