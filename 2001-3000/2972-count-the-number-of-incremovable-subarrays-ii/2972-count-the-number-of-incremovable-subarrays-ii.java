class Solution {
    public long incremovableSubarrayCount(int[] nums) {
        
        int n = nums.length;

        int left = 0;

        // Find the longest increasing prefix
        while (left + 1 < n && nums[left] < nums[left + 1]) {
            left++;
        }

        // Entire array is already strictly increasing
        if (left == n - 1) {
            return (long) n * (n + 1) / 2;
        }

        long answer = left + 2;

        for (int j = n - 1; j > 0; j--) {

            while (left >= 0 && nums[left] >= nums[j]) {
                left--;
            }

            answer += left + 2;

            if (nums[j - 1] >= nums[j]) {
                break;
            }
        }

        return answer;
    }
}