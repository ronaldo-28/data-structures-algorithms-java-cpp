class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double low = nums[0];
        double high = nums[0];
        for (int num : nums) {
            low = Math.min(low, num);
            high = Math.max(high, num);
        }
        while (low + 1e-5 < high) {
            double mid = low + (high - low) / 2;
            if (isFeasible(nums, k, mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private boolean isFeasible(int[] nums, int k, double target) {
        double sum = 0;
        double preSum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i >= k && sum >= 0) {
                return true;
            }
            sum += nums[i] - target;
            if (i >= k) {
                preSum += nums[i - k] - target;
                if (preSum < 0) {
                    sum -= preSum;
                    preSum = 0;
                }
            }
        }
        return sum >= 0;
    }


}