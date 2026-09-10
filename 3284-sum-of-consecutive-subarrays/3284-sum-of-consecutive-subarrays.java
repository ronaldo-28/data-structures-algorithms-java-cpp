class Solution {
    public int getSum(int[] nums) {
        final long MOD = 1_000_000_007L;

        long ans = nums[0];
        long curr = nums[0];

        int left = 0;
        int prior = 0;

        for (int right = 1; right < nums.length; right++) {
            int diff = nums[right] - nums[right - 1];

            if (Math.abs(diff) != 1) {
                prior = 0;
                left = right;
                curr = 0;
            } else if (prior != diff) {
                curr = nums[right - 1];
                left = right - 1;
                prior = diff;
            }

            curr += (long) nums[right] * (right - left + 1);
            ans = (ans + curr) % MOD;
        }

        return (int) ans;
    }
}