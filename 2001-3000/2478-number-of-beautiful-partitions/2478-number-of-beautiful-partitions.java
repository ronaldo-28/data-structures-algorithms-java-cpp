class Solution {
    public int beautifulPartitions(String s, int k, int minLength) {
        int n = s.length();
        char[] str = s.toCharArray();
        int MOD = 1_000_000_007;

        // Early exit: First character MUST be prime, Last character MUST be non-prime
        if (!isPrime(str[0]) || isPrime(str[n - 1])) {
            return 0;
        }

        // dp[i][j] = number of ways to form 'j' partitions using the prefix up to index 'i'
        int[][] dp = new int[n + 1][k + 1];
        dp[0][0] = 1;

        // runningSum[j] tracks the cumulative valid partitioning combinations for 'j' partitions
        int[] runningSum = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            // A potential split index occurs at (i - minLength)
            int prevSplit = i - minLength;
            if (prevSplit >= 0) {
                // Condition to start a new partition right after prevSplit:
                // It must either be the start of the string (0) or a valid boundary between non-prime and prime
                if (prevSplit == 0 || (!isPrime(str[prevSplit - 1]) && isPrime(str[prevSplit]))) {
                    for (int j = 0; j < k; j++) {
                        runningSum[j + 1] = (runningSum[j + 1] + dp[prevSplit][j]) % MOD;
                    }
                }
            }

            // Check if current index 'i' is a valid ending point for any partition
            // Current character must be non-prime AND the next one (if it exists) must be prime
            if (!isPrime(str[i - 1]) && (i == n || isPrime(str[i]))) {
                for (int j = 1; j <= k; j++) {
                    dp[i][j] = runningSum[j];
                }
            }
        }

        return dp[n][k];
    }

    // Inline-friendly primitive lookups are significantly faster than sets or switch blocks
    private boolean isPrime(char c) {
        return c == '2' || c == '3' || c == '5' || c == '7';
    }
}