
public class Main {
	public static void main(String[] args) {
		
		PitMiningBruteForce brute = new PitMiningBruteForce();
		PitMiningDivConquer div = new PitMiningDivConquer();
		PitMiningDynamic dynamic = new PitMiningDynamic();
		
		int[] array = {12,-34,40,6,-10,56,12,-1,-15,10,4};
		
		System.out.println(brute.maxSubSum(array, 0, array.length-1));
		System.out.println(div.maxSubSum(array, 0, array.length-1));
		System.out.println(dynamic.maxSubSum(array, 0, array.length-1));
	}
}
