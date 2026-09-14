class Solution {
    public long countGoodSubarrays(int[] nums) {
        int n = nums.length;
        long ans = 0;
        int[] prev = new int[n];
        for(int i = 0; i < n; i++) {
            int left = i - 1;
            while(left >= 0 && (nums[left] | nums[i]) == nums[i]) left = prev[left];
            prev[i] = left;
            int right = i + 1;
            while(right < n && nums[right] != nums[i] && (nums[right] | nums[i]) == nums[i]) right++;
            ans += (i - left) * (right - i);
        }
        return ans;
    }
}