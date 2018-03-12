
public class PitMiningBruteForce {
	public int maxSubSum(int[] a, int left, int right){
		int sum = 0;
		int max = a[left];
		for(int i = left; i<= right; i++){
			sum = 0;
			for(int j = i; j <= right; j++){
				sum += a[j];
				if(sum >= max){
					max = sum;
				}
			}
		}
		return max;
	}
}
