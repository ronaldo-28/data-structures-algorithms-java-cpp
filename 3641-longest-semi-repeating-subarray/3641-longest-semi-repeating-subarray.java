class Solution {
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] count = new int[100001];
        int res = 0, left = 0, repeatCount = 0;
        for (int right = 0; right < n; right++) {
            count[nums[right]]++;
            if (count[nums[right]] == 2) repeatCount++;
            while (repeatCount > k) {
                count[nums[left]]--;
                if (count[nums[left]] == 1) repeatCount--;
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}