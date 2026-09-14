class Solution {
    public int maximumANDSum(int[] nums, int numSlots) {
        int[] dp = new int[(int) Math.pow(3, numSlots)];
        Arrays.fill(dp, -1);
        return backtracking(0, 0, nums, numSlots, dp);
    }

    public int backtracking(int i, int mask, int[] nums, int numSlots, int[] dp) {
        if (i == nums.length) return 0;

        if (dp[mask] != -1) return dp[mask];

        int ans = 0, pow = 1;

        for (int j=1; j<=numSlots; j++, pow *= 3) {
            if ((mask / pow) % 3 < 2) {
                ans = Math.max(ans, (nums[i] & (j)) + backtracking(i+1, mask+pow, nums, numSlots, dp));
            }
        }
        return dp[mask] = ans;
    }
}