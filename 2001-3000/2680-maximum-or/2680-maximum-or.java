class Solution {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long[] suffixOR = new long[n+1];
        suffixOR[n] = 0;
        for (int i = n-1; i >= 0; i--) {
            suffixOR[i] = suffixOR[i+1] | nums[i];
        }

        long prefixOr = 0, maxOr = 0;
        for (int i = 0; i < n; i++) {
            maxOr = Math.max(maxOr, prefixOr | ((long)nums[i] << k) | suffixOR[i+1]);
            prefixOr = prefixOr | nums[i];
        }

        return maxOr;
    }
}