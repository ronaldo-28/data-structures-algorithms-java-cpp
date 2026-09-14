class Solution {
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        int sum = 0;
        int last = nums[0];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i + 1] > nums[i] && nums[i] > nums[i - 1]) sum++;
            if (nums[i] > last) {
                dp[i] = true;
                last = nums[i];
            }
        }

        last = nums[n - 1];
        for (int i = n - 2; i > 0; i--) {
            if (nums[i] < last) {
                if (dp[i]) sum++;
                last = nums[i];
            }
        }

        return sum;
    }
}