/**
 * Project #6 Movie Review Sentiment
 * @author Evan ElkinEvan Elkin and Ryan Kugel
 * 
 * Most Positive: impersonation
 * Most Negative: pics(?)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/* Main method and algorithm for determining sentiment */
public class MovieReview {

	// Instance variable: HashMap for storing sentiment of words
	HashMap<String, Sentiment> map;
	// main algorithm
	public void start() {
		//learn
		learn();
		Scanner sc=new Scanner(System.in);;
		// while user supplies input
		// prompt for user input
		// compute the average sentiment score
		// display sentiment score
		System.out.println("Please submit a movie review.");
		while(sc.hasNext()){
			String movieReview= sc.nextLine();

			Scanner lineScanner= new Scanner(movieReview);
			double reviewSum=0;
			double wordCount=0;
			double reviewAverage;
			while(lineScanner.hasNext()){
				String word= lineScanner.next().toLowerCase();
				word=word.replaceAll("\\W", "");
				wordCount++;
				if(!(map.containsKey(word))){
					reviewSum+=2;
				} else{
					reviewSum+= (map.get(word).getAverage());
				}
			}
			reviewAverage=reviewSum/wordCount;
			System.out.println("This review has an average value of "+reviewAverage+".");
			if(reviewAverage<2){
				System.out.println("Negative Sentiment.");
			} else{
				System.out.println("Positive Sentiment.");
			}
			System.out.println("Please submit another review.");
		}


	}

	// read text file and create HashMap<String, Sentiment>
	public void learn() {
		//create the map
		map= new HashMap<String, Sentiment>();
		//reads the file
		File file= new File("movieReviews.txt");
		try {
			Scanner sc= new Scanner(file);

			while(sc.hasNext()){
				int s= sc.nextInt();
				String line= sc.nextLine();
				Scanner lineScanner= new Scanner(line);

				while(lineScanner.hasNext()){
					String word= lineScanner.next().toLowerCase();
					word= word.replaceAll("\\W", "");
					if(map.containsKey(word)&& !word.equals("")){
						map.get(word).addSentiment(s);
						map.put(word, map.get(word));
					} else{
						Sentiment wordSentiment=new Sentiment(word);
						map.put(word, wordSentiment); //new entry
						map.get(word).addSentiment(s);
						map.put(word, map.get(word));
					}
				}
			}
			//finds the most positive word
			Set<String> keys= map.keySet();
			String largest="";
			double largestValue= 0;
			for(String key:keys){
				double value= map.get(key).getAverage();
				if(value>largestValue){
					largestValue= value;
					largest= key;
				}
			}
			//finds the most negative word
			String smallest="";
			double smallestValue= 5;
			for(String key:keys){
				double value= map.get(key).getAverage();
				if(value<smallestValue){
					smallestValue= value;
					smallest= key;
				}
			}

			//System.out.println("Most positive= "+largest+", "+largestValue);
			//System.out.println("Most negative= "+smallest+", "+smallestValue);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// create a new instance of MovieReview and call the start method
	public static void main(String [] args) {
		new MovieReview().start();
	}
}