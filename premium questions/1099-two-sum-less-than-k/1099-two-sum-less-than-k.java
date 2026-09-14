class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        return brute_force(nums, k);
    }

    private int brute_force(int[] nums, int k) {
        int n = nums.length;
        int minSum = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                if (sum < k && minSum < sum) minSum = sum;
            }
        }

        return minSum;
    }
}