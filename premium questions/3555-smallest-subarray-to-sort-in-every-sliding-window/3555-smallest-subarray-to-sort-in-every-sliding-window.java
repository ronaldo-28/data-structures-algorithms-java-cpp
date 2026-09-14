class Solution {
    public int[] minSubarraySort(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];

        for (int start = 0; start <= n - k; start++) {
            int left = -1, right = -1;

            for (int i = start; i < start + k - 1; i++) {
                if (nums[i] > nums[i + 1]) {
                    if (left == -1) left = i;
                    right = i + 1;
                }
            }

            if (left == -1) {
                ans[start] = 0; 
                continue;
            }

            int windowLeft = start;
            int windowRight = start + k - 1;

            int minVal = Integer.MAX_VALUE;
            int maxVal = Integer.MIN_VALUE;

            for (int i = left; i <= right; i++) {
                minVal = Math.min(minVal, nums[i]);
                maxVal = Math.max(maxVal, nums[i]);
            }

            while (left > windowLeft && nums[left - 1] > minVal) {
                left--;
            }

            while (right < windowRight && nums[right + 1] < maxVal) {
                right++;
            }

            ans[start] = right - left + 1;
        }

        return ans;
    }
}