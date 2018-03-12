

public class PitMiningDivConquer {
	public int maxSubSum(int[] a, int left, int right){
		// Only one space? Dig it if it's positive only!
		if (left == right){
			return Math.max(0, a[left]);
		}
		// Recurse
		int center = (left+right)/2;
		int maxLeftSum = maxSubSum(a,left,center);
		int maxRightSum = maxSubSum(a,center+1,right);
		
		// Compute answer if it spans the middle.
		int maxLeftBorderSum = 0;
		int leftBorderSum = 0;
		for(int i = center; i>= left; i--){
			leftBorderSum += a[i];
			maxLeftBorderSum = Math.max(maxLeftBorderSum, leftBorderSum);
		}
		
		int maxRightBorderSum = 0;
		int rightBorderSum = 0;
		for(int i = center+1; i<=right; i++){
			rightBorderSum += a[i];
			maxRightBorderSum = Math.max(maxRightBorderSum, rightBorderSum);
		}
		
		int maxMiddle = maxLeftBorderSum + maxRightBorderSum;
		
		// Return biggest of 3 possibilities.
		return (Math.max(maxMiddle, Math.max(maxLeftSum, maxRightSum)));
	}
}


