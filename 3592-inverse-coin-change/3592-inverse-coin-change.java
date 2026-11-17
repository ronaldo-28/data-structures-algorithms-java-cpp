class Solution {
    public List<Integer> findCoins(int[] numWays) {
        int n = numWays.length;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        List<Integer> ans = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            if(numWays[i - 1] > dp[i]) {
                ans.add(i);
                for(int j = i; j <= n; j++) {
                    dp[j] += dp[j - i];
                }
                if(numWays[i - 1] > dp[i]) return new ArrayList<>();
            } else if(numWays[i - 1] < dp[i]) {
                return new ArrayList<>();
            }
        }
        return ans;
    }
}