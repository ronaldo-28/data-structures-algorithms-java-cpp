class Solution {
    public int maxTastiness(int[] price, int[] tastiness, int maxAmount, int maxCoupons) {
        int n = price.length;
        int[][] dp = new int[maxAmount + 1][maxCoupons + 1];
        for (int[] d : dp) Arrays.fill(d, -1);
        for (int i = 0; i <= maxCoupons; i++) dp[0][i] = 0;
        for (int i = 0; i < n; i++) {
            int currentFruit = tastiness[i];
            int currentPrice = price[i];
            int halfPrice = currentPrice / 2;
            for (int j = 0; j <= maxCoupons; j++) {
                for (int k = maxAmount - halfPrice; k >= 0; k--) {
                    int currentMaxTaste = dp[k][j];
                    if (currentMaxTaste != -1) {
                        if (k <= maxAmount - currentPrice) {
                            dp[k + currentPrice][j] = Math.max(dp[k + currentPrice][j], currentMaxTaste + currentFruit);
                        }
                        if (j != 0) {
                            dp[k + halfPrice][j - 1] = Math.max(dp[k + halfPrice][j - 1], currentMaxTaste + currentFruit);
                        }
                    }
                }
            }
        }
        int output = 0;
        for (int[] d : dp) {
            for (int i : d) output = Math.max(output, i);
        }
        return output;
    }



}