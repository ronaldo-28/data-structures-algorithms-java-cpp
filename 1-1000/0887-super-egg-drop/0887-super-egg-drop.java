class Solution {
    public int superEggDrop(int k, int n) {

        // dp[e] = maximum floors that can be checked
        // with current number of moves and e eggs
        long[] dp = new long[k + 1];

        int moves = 0;

        // Increase moves until we can cover n floors
        while (dp[k] < n) {
            moves++;

            // Update dp from right to left
            for (int e = k; e >= 1; e--) {
                dp[e] = dp[e] + dp[e - 1] + 1;
            }
        }

        return moves;
    }
}