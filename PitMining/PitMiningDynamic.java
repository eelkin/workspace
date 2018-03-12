
public class PitMiningDynamic {
	public int maxSubSum(int[] a, int left, int right){
		// what's the best we can do if we must dig at a[i]?
		/*
		// max sum going from left->right
		int maxSumLeft = 0;
		// max sum going from right->left
		int maxSumRight = 0;
		int li = 0;
		int ri = 0;
		int finalSum = 0;
		int sum = 0;
		for(int i = 0; i < a.length; i++){
			sum += a[i];
			if(sum > maxSumLeft){
				maxSumLeft = sum;
				ri = i;
			}
		}
		sum = 0;
		for(int i = a.length-1; i >= 0; i--){
			sum += a[i];
			if(sum > maxSumRight){
				maxSumRight = sum;
				li = i;
			}
		}
		for(int i = li; i <= ri; i++){
			finalSum += a[i];
		}
		return finalSum;
		*/
		int n = right - left + 1;
		int[] max = new int[n];
		max[0] = a[0];
		int best = a[0];
		for(int i = 1; i < n; i++){
			// don't keep stuff to left
			if(max[i-1] < 0) {
				max[i] = a[i];
			}
			// keep stuff to left
			else {
				max[i] = max[i-1] + a[i];
			}
			
			if(max[i] > best) {
				best = max[i];
			}
		}
		return best;
	}
}
