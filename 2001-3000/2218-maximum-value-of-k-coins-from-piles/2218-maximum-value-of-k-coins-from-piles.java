class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int[] dp = new int[k + 1];

        for (List<Integer> pile : piles) {
            int[] newDp = dp.clone();
            int prefixSum = 0;

            for (int i = 1; i <= Math.min(pile.size(), k); i++) {
                prefixSum += pile.get(i - 1);

                for (int j = i; j <= k; j++) {
                    newDp[j] = Math.max(newDp[j], dp[j - i] + prefixSum);// for i = 1, use 1 element from curr pile + (k - 1) elements from prev piles
                }
            }

            dp = newDp;
        }

        return dp[k];
    }
}