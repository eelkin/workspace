package main;

import id3.*;
import problems.*;
import tree.*;

/*
 * Shannon Duvall
 * Evan Elkin
 * Main.java
 */
public class Main {
	public static void main(String[] args) {
		RestaurantProblem rest = new RestaurantProblem();
		EvanProblem ev = new EvanProblem();
		ID3 id3 = new ID3();
		StringWriter.turnOff();
		TreeNode restaurant = id3.id3(rest.getExamples(), rest.getAttributes(), rest.getDefaultDecision());
		TreeNode evan = id3.id3(ev.getExamples(), ev.getAttributes(), ev.getDefaultDecision());
		System.out.println("Print Restaurant Tree: ");
		StringWriter.turnOn();
		restaurant.printMe();
		System.out.println("\nEvan's Concert Problem Tree: ");
		evan.printMe();
	}

}
