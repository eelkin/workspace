/*
 * Evan Elkin
 * The problem of whether or not to go to a concert
 */
package problems;

import java.util.*;

import id3.Example;
import tree.Attribute;
import tree.Decision;

public class EvanProblem {
	// The attributes for the problem - will be initialized later
		private ArrayList<Attribute> attributes = new ArrayList<Attribute>();
		// The training examples- will be initialized later
		private ArrayList<Example> examples = new ArrayList<Example>();
		
		// The decisions for this problem
		private Decision go = new Decision("Go");
		private Decision dont = new Decision("Don't Go");
		
		// These are some String choice sets for various questions.
		private String[] money = {"$","$$","$$$"};
		private String[] genres = {"Rock", "Pop", "Rap", "Country"};
		private String[] times = {"Day", "Night"};
		private String[] places = {"In-state", "Out-of-state", "Out-of-country"};
		private String[] sizes = {"Small", "Medium", "Large"};
		
		
		public EvanProblem(){
			makeAttributes();
			makeExamples();
		}
		
		public List<Attribute> getAttributes() {
			return attributes;
		}
		
		public List<Example> getExamples() {
			return examples;
		}
		
		public Decision getDefaultDecision() {
			return go;
		}
		
		/*
		 * Initialize the attributes for this problem.
		 */
		public void makeAttributes(){
			// Here are the attributes for the restaurant decision
			// Each attribute needs a String description and an array of answer 
			// possibilities.

			attributes.add(new Attribute("Time?", times));
			attributes.add(new Attribute("Distance?", places));
			attributes.add(new Attribute("Size?", sizes));
			attributes.add(new Attribute("Ticket Price?", money));
			attributes.add(new Attribute("Genre?", genres));
		}
		
		/*
		 * This initializes the Examples.  It depends on the 
		 * attributes being initialized already, so call that first.
		 */
		public void makeExamples(){
			// Shortcut for me to enter the examples...  
			// Each index tells the value of that attribute
			int[][] noExamples = {
					/*
					{0, 1, 1, 1, 3},
					{0, 2, 2, 2, 1},
					{0, 1, 1, 2, 3},
					{0, 2, 1, 1, 0},
					{0, 1, 2, 2, 3},
					{0, 2, 2, 2, 2}	
					*/
					{1, 2, 1, 1, 1},
					{0, 1, 2, 1, 3},
					{0, 0, 1, 2, 2},
					{0, 2, 2, 2, 1},
					{1, 2, 2, 1, 0},
					{1, 1, 1, 2, 3},
					{0, 0, 2, 0, 2}
			};
			int[][] yesExamples = {
					/*
					{1, 0, 0, 0, 0},
					{1, 0, 0, 0, 3},
					{1, 1, 2, 1, 1},
					{0, 1, 2, 2, 0},
					{1, 0, 1, 1, 2},
					{1, 1, 2, 1, 2}
					*/
					{0, 0, 2, 0, 2},
					{1, 0, 1, 1, 0},
					{0, 1, 2, 1, 0},
					{1, 0, 0, 0, 3},
					{1, 1, 1, 0, 0},
					{1, 0, 2, 0, 1},
					{0, 0, 1, 0, 1}
			};

			// This will make my life easier later.  I'm making a little hashmap of all  
			// attributes to their list of possible answers. 
			HashMap<Attribute, String[]> attTypes = new HashMap<Attribute, String[]>();

			attTypes.put(attributes.get(0), times);
			attTypes.put(attributes.get(1), places);
			attTypes.put(attributes.get(2), sizes);
			attTypes.put(attributes.get(3), money);
			attTypes.put(attributes.get(4), genres);
			
			// Now actually populate the examples
			for(int i = 0; i<noExamples.length; i++){
				HashMap<Attribute, String> attValue = new HashMap<Attribute, String>();
				for(int j = 0; j< noExamples[i].length; j++){
					attValue.put(attributes.get(j),attTypes.get(attributes.get(j))[noExamples[i][j]]);

				}
				examples.add(new Example(attValue, dont));
			}

			for(int i = 0; i<yesExamples.length; i++){
				HashMap<Attribute, String> attValue = new HashMap<Attribute, String>();
				for(int j = 0; j< yesExamples[i].length; j++){
					attValue.put(attributes.get(j),attTypes.get(attributes.get(j))[yesExamples[i][j]]);
				}
				examples.add(new Example(attValue, go));
			}
			// print examples to see if they look right to start with.
			//for(Example e: examples){
			//	e.printMe();
			//}
		}
}
