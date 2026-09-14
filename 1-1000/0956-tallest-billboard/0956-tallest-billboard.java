class Solution {
    public int tallestBillboard(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int[] cur = new int[sum + 1];
        Arrays.fill(cur, -1);
        cur[0] = 0;
        for (int i : arr) {
            int[] dp = cur.clone();
            for (int diff = 0; diff < dp.length; diff++) {
                if (cur[diff] == -1) {
                    continue;
                }
                // if (diff + i <= sum) {
                    dp[diff + i] = Math.max(dp[diff + i], cur[diff] + i);
                // } 
                int newDiff = Math.abs(diff - i);
                int newHeight = Math.max(cur[diff], cur[diff] - diff + i);
                dp[newDiff] = Math.max(dp[newDiff], newHeight);
            }
            cur = dp;
        }
        return cur[0] == -1 ? 0 : cur[0];
    }
}