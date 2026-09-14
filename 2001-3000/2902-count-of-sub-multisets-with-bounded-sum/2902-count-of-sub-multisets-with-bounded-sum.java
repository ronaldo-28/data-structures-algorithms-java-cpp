import java.util.List;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int countSubMultisets(List<Integer> nums, int l, int r) {
        // Step 1: Compute frequencies using a primitive array instead of a Map
        int maxVal = 0;
        int totalSum = 0;
        for (int num : nums) {
            if (num > maxVal) maxVal = num;
            totalSum += num;
        }

        // Fast fail optimizations
        if (l > totalSum) return 0;
        r = Math.min(r, totalSum);

        int[] count = new int[maxVal + 1];
        for (int num : nums) {
            count[num]++;
        }

        // Step 2: Set up our DP table. dp[i] holds combinations to achieve sum 'i'.
        int[] dp = new int[r + 1];
        dp[0] = 1; // Base case: 1 way to make a sum of 0 (empty set)

        int currentMaxSum = 0;

        // Step 3: Process every unique number > 0 via sliding window prefix calculation
        for (int num = 1; num <= maxVal; num++) {
            int freq = count[num];
            if (freq == 0) continue;

            currentMaxSum = Math.min(r, currentMaxSum + num * freq);

            // Forward Pass: Accumulate consecutive combinations step transitions
            for (int i = num; i <= currentMaxSum; i++) {
                dp[i] = (dp[i] + dp[i - num]) % MOD;
            }

            // Backward Pass (Correction part): Exclude transitions exceeding 'freq' limit
            int windowSize = (freq + 1) * num;
            for (int i = currentMaxSum; i >= windowSize; i--) {
                dp[i] = (dp[i] - dp[i - windowSize] + MOD) % MOD;
            }
        }

        // Step 4: Accumulate the valid combinations across the target range [l, r]
        long ans = 0;
        for (int i = l; i <= r; i++) {
            ans = (ans + dp[i]) % MOD;
        }

        // Step 5: Account for zeros. Zeros don't affect sum, but they multiply choices.
        // Each zero can either be excluded or included 1, 2, ... count[0] times.
        ans = (ans * (count[0] + 1)) % MOD;

        return (int) ans;
    }
}