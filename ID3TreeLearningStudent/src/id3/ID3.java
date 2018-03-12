package id3;
/*
 * Author: Evan Elkin
 * ID3 Algorithm Implemented
 */
import java.util.*;

import tree.Attribute;
import tree.Decision;
import tree.TreeNode;
public class ID3 {

	// The ID3 Algorithm takes a collection of examples, the collection of Attributes used for making 
	// the decision, and a default Decision.  
	// It returns the decision tree.
	public TreeNode id3(List<Example> examples, List<Attribute> attributes, Decision defaultDecision){
		//if there are no examples
		if(examples.isEmpty()) {
			return defaultDecision;
		}
		//all examples have same classification - decision
		else if(allSame(examples)) {
			return examples.get(0).getDecision();
		}
		//if there are no attributes
		else if(attributes.isEmpty()) {
			return majorityValue(examples);
		}
		else {
			//best <- chooseAttribute (attributes, examples)
			Attribute best = chooseAttribute(attributes, examples);
			//tree <- decision tree with root test best
			Attribute tree = best;

			//instantiates hashmap with key being attribute values 
			//and value being the list of examples with  
			for(String value : best.getPossibleAnswers()) {
				ArrayList<Example> valExamples = new ArrayList<Example>();
				for(Example e : examples) {
					if(e.getValue(best).equals(value)) {
						valExamples.add(e);
					}
				}
				ArrayList<Attribute> otherAttr = (ArrayList) attributes;
				otherAttr.remove(best);
				TreeNode subtree = id3(valExamples, otherAttr, majorityValue(examples));
				tree.addChild(value, subtree);
			}
			
			return tree;
		}
	}

	private boolean allSame(List<Example> examples) {
		//checks to see if all examples have same classifier
		//boolean allSame = true;
		String decision = examples.get(0).getDecisionName();
		for(Example e : examples) {
			if(!decision.equals(e.getDecisionName())) {
				return false;
			}
		}
		return true;
	}

	private Decision majorityValue(List<Example> examples) {
		//return MAJORITY-VALUE(examples)

		HashMap<String, Integer> myDecisions = new HashMap<String, Integer>();
		for(Example e : examples) {
			//count number of decisions
			String dName = e.getDecisionName();
			if(!myDecisions.containsKey(dName)) {
				myDecisions.put(dName, 1);
			} else {
				int old = myDecisions.get(dName);
				myDecisions.replace(dName, old+1);
			}
		}
		String maj = "";
		int majCount = Integer.MIN_VALUE;
		for(String d : myDecisions.keySet()) {
			if(myDecisions.get(d) > majCount) {
				maj = d;
				majCount = myDecisions.get(d);
			}
		}
		return new Decision(maj);
	}

	//chooses best attribute based on entropy --> info remaining
	private Attribute chooseAttribute(List<Attribute> attributes, List<Example> examples) {
		//refactor to include different decisions
		HashMap<String, Double> myDecisions = new HashMap<String, Double>();
		//double exampleSize = examples.size();
		double totalInfo = 0;
		
		//fills myDecisions dictionary
		for(Example e : examples) {
			//count number of decisions
			String dName = e.getDecisionName();
			if(!myDecisions.containsKey(dName)) {
				myDecisions.put(dName, 1.0);
			} else {
				double old = myDecisions.get(dName);
				myDecisions.replace(dName, old+1);
			}
		}

		totalInfo = calculateEntropy(myDecisions);
		
		Attribute best = null;
		double bestInfoGain = 0;
		for(Attribute attribute: attributes) {
			double infoGain = totalInfo - expectedInfoLeft(attribute, examples);
			//got rid of >=
			if(infoGain > bestInfoGain) {
				bestInfoGain = infoGain;
				best = attribute;
			}
		}

		return best;
	}

	//calculates expected value of attribute
	private double expectedInfoLeft(Attribute attribute, List<Example> examples) {
		//for each attribute value, stores count for each decisions
		HashMap<String, HashMap<String, Double>> values = new HashMap<String, HashMap<String, Double>>();
		//how many of each attribute value 
		HashMap<String, Double> totalValues = new HashMap<String, Double>();
		
		//adds keys for value dictionary and total value dictionary
		for(String val : attribute.getPossibleAnswers()) {
			values.put(val, new HashMap<String, Double>());
			totalValues.put(val, 0.0);
		}
		
		//fills decision dictionary for each value
		for(String val : values.keySet()) {
			HashMap<String, Double> myDecisions = values.get(val);
			for(Example e : examples) {
				String decision = e.getDecisionName();
				//if the example has the attribute value desired
				if(val.equals(e.getValue(attribute))) {
					//adds one to total num of value
					double i = totalValues.get(val);
					totalValues.replace(val, i+1);
					//finds out decision of note
					if(!myDecisions.containsKey(decision)) {
						myDecisions.put(decision, 1.0);
					} else {
						double old = myDecisions.get(decision);
						myDecisions.replace(decision, old+1);
					}
				}
			}
		}
		
		//eil = expected info left
		double eil = 0;
		
		//calculates eil based on expected values of entropy per value of attribute
		for(String value : attribute.getPossibleAnswers()) {
			eil += (totalValues.get(value)/examples.size())*calculateEntropy(values.get(value));
		}
		return eil;
	}
	
	private double calculateEntropy(HashMap<String, Double> d) {
		double entropy = 0;
		double numDecisions = 0;
		
		//finds number of total decisions (equal to example size or example size per value)
		for(double num : d.values()) {
			numDecisions += num;
		}
		//calculates entropy
		for(double num : d.values()) {
			double prob = 0;
			if(num == 0.0) {
				prob = 0;
			}
			prob = num/numDecisions;
			entropy += prob * log2(prob);
		}
		return -1 * entropy;
	}
	
	private double log2(double n) {
		return Math.log(n)/Math.log(2);
	}
}
