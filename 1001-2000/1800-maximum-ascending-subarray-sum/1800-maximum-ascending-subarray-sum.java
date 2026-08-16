class Solution {
    public int maxAscendingSum(int[] nums) {
        // Step 1: Initialize the variables
        int maxSum = nums[0];
        int currentSum = nums[0];
        
        // Step 2: Iterate through the array starting from the second element
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {  // If the current number continues the ascending order
                currentSum += nums[i];
            } else {  // Ascending order broken, update maxSum and reset currentSum
                maxSum = Math.max(maxSum, currentSum);
                currentSum = nums[i];
            }
        }
        
        // Step 3: Final update in case the last subarray is the maximum one
        maxSum = Math.max(maxSum, currentSum);
        return maxSum;
    }
}
