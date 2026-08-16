class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        // Fast primitive array instead of a HashMap
        int maxVal = 0;
        for (int num : nums) if (num > maxVal) maxVal = num;
        
        int[] lastSeen = new int[maxVal + 1];
        Arrays.fill(lastSeen, -1);
        
        long sum = 0, maxSum = 0; 
        int left = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int index = lastSeen[nums[i]]; // O(1) direct memory access
            
            while (left <= index) {
                sum -= nums[left++];
            }
            if (i - left >= k) {
                sum -= nums[left++];
            }
            if (i - left == k - 1) {
                maxSum = Math.max(maxSum, sum);
            }
            lastSeen[nums[i]] = i;
        }
        return maxSum;
    }
}