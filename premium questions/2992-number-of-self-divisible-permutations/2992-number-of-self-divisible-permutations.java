class Solution {
    Integer[] dp;
    public int selfDivisiblePermutationCount(int n) {
        dp = new Integer[1 << n];
        return dfs(n, 0);
    }

    int dfs(int n, int mask) {
        if(((1 << n) - 1) == mask) {
            return 1;
        }
        if(dp[mask] != null) {
            return dp[mask];
        }
        int curr = 0;
        for(int i = 0; i < n; i++) {
            if(((mask >> i) & 1) == 0 && gcd(i + 1, Integer.bitCount(mask) + 1) == 1) {
                curr += dfs(n, mask | (1 << i));
            }
        }
        return dp[mask] = curr;
    }

    int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}