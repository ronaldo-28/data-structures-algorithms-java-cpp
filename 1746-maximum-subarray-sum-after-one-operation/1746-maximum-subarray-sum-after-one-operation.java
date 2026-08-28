class Solution {
    public int maxSumAfterOperation(int[] nums) {
        // kadanes
        int len = nums.length, sum = 0, maxSum = 0;
        int[] suff = new int[len + 1];

        for (int i = len - 1; i >= 1; i--){
            sum += nums[i];
            if (sum < 0) sum = 0;
            suff[i] = sum;
        }

        sum = 0;

        for (int i = 0; i < len; i++){
            int x = nums[i];
            maxSum = Math.max(maxSum, x * x + sum + suff[i + 1]);

            sum += x;
            if (sum < 0) sum = 0;
        }

        return maxSum;
    }
}