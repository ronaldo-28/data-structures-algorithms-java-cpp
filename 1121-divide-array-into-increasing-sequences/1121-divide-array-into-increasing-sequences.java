class Solution {
    public boolean canDivideIntoSubsequences(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; ) {
            int curr = nums[i], cnt = 0;
            while (i < n && curr == nums[i]) {
                i++;
                cnt++;
            }
            if (cnt * k > n) {
                return false;
            }
        }
        return true;
    }
}