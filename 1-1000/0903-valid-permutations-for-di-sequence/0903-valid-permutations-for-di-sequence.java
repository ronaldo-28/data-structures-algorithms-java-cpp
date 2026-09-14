class Solution {
    public int numPermsDISequence(String s) {
        int n = s.length();
        int mod = 1_000_000_007;

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 0; i < n; i++) {
            int[] next = new int[n + 1];

            if (s.charAt(i) == 'I') {
                int sum = 0;
                for (int j = 0; j <= i; j++) {
                    sum = (sum + dp[j]) % mod;
                    next[j + 1] = sum;
                }
            } else {
                int sum = 0;
                for (int j = i; j >= 0; j--) {
                    sum = (sum + dp[j]) % mod;
                    next[j] = sum;
                }
            }

            dp = next;
        }

        int ans = 0;
        for (int x : dp) {
            ans = (ans + x) % mod;
        }
        return ans;
    }
}