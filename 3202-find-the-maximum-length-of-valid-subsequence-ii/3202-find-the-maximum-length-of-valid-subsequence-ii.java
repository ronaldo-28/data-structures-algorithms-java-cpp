class Solution {
    public int maximumLength(int[] nums, int k) {
        int length = nums.length;
        if (k == 1) {
            return length;
        }
        int res = 2;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = nums[i] % k;
        }
        for (int i = 0; i < k; i++) {
            int[] dp = new int[k];
            for (int j = 0; j < length; j++) {
                dp[arr[j]] = dp[(i - arr[j] + k) % k] + 1;
                res = Math.max(res, dp[arr[j]]);
            }
        }
        return res;
    }
}