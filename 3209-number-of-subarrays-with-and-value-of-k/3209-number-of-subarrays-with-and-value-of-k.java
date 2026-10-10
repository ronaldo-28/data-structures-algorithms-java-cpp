public class Solution {
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        int left = 0;
        int right = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            for (int j = i - 1; j >= 0 && (nums[j] & x) != nums[j]; j--) {
                nums[j] &= x;
            }
            while (left <= i && nums[left] < k) {
                left++;
            }
            while (right <= i && nums[right] <= k) {
                right++;
            }
            ans += right - left;
        }
        return ans;
    }
}