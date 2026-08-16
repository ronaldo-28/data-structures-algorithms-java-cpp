class Solution {
    public int longestArithmetic(int[] nums) {
        int n = nums.length, ans = 2;
        int l = 0, m, r;
        while (l < n - 1) {
            m = l + 1;
            int diff = nums[l + 1] - nums[l];
            while (m < n && diff == nums[m] - nums[m - 1]) {
                m++;
            }
            ans = Math.max(ans, m - l + ((l > 0 || m < n) ? 1 : 0));
            if (l >= 2 && nums[l] - nums[l - 2] == 2 * diff) {
                ans = Math.max(ans, m - l + 2);
            }
            r = m + 1;
            if (r < n && nums[r] - nums[r - 2] == 2 * diff) {
                while (r + 1 < n && nums[r + 1] - nums[r] == diff) {
                    r++;
                }
                ans = Math.max(ans, r - l + 1);
            }
            l = m - 1;
        }
        return ans;
    }
}