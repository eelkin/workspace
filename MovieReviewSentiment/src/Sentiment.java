/**
 * Project #6 Movie Review Sentiment
 * @author Evan Elkin and Ryan Kugel
 *
 */
/* Used to maintain the current sentiment of some word */
public class Sentiment {
	// Instance variables: sentiment sum and word count
	private int sentimentSum;
	private int wordCount;

	// Constructor(s)
	public Sentiment(String word){
		sentimentSum=0;
		wordCount=0;
	}
	// add the new sentiment to the sum, increase the word count
	public void addSentiment(int sentiment) {
		wordCount++;
		sentimentSum+=sentiment;
	}

	// return the average sentiment
	public double getAverage() {
		return (double)sentimentSum/wordCount;
	}
}

