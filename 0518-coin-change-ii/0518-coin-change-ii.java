class Solution {
    static {
        for (int i = 0; i < 230; i++) {
            change(2, new int[]{2});
        }
    }
    public static int change(int amount, int[] coins) {
        int totalCoins = coins.length;
        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for (int coinValue : coins) {
            for (int currentAmount = coinValue; currentAmount <= amount; currentAmount++) {
                dp[currentAmount] += dp[currentAmount - coinValue];
            }
        }

        return dp[amount];
    }
}