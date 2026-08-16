import java.util.Arrays;
import java.util.List;

class Solution {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];

        // Use a safe negative infinity to prevent overflow when adding 1
        int INF = -10000;
        Arrays.fill(dp, INF);
        dp[0] = 0;

        int currentSum = 0;

        // 1. Iterate forwards, extracting the value once
        for (int x : nums) {
            // 2. Skip elements strictly larger than our target
            if (x > target) {
                continue;
            }

            currentSum += x;

            // 3. Cap the max capacity we need to check at our current total sum
            int maxV = Math.min(target, currentSum);

            // 4. Stop the loop at x. Values below x don't change.
            for (int v = maxV; v >= x; v--) {
                if (dp[v - x] != INF) {
                    dp[v] = Math.max(dp[v], 1 + dp[v - x]);
                }
            }
        }

        return dp[target] <= 0 ? -1 : dp[target];
    }
}