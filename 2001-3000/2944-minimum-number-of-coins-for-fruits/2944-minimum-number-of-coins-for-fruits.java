class Solution {
	public int minimumCoins(int[] prices) {
		int[] costByNumberOfFree = new int[2 * prices.length + 1];
		int minValue = prices[0];
		costByNumberOfFree[2] = minValue;
		for (int i = 1; i < prices.length; i++) {
			int p = prices[i];
			int min = minValue;
			if (costByNumberOfFree[i] == minValue) {
				minValue = Integer.MAX_VALUE; 
				for (int j = i + 1; j <= 2 * i; j++) {
					if (costByNumberOfFree[j] < minValue && costByNumberOfFree[j] != 0) {
						minValue = costByNumberOfFree[j];
					}
				}
			}
			int n = 2 * i + 2;
			costByNumberOfFree[n] = min + p;
			if (costByNumberOfFree[n] < minValue) {
				minValue = costByNumberOfFree[n];
			}
		}

		return minValue;
	}
}