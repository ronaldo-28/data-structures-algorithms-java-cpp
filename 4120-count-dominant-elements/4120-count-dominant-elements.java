class Solution {
    public int countDominantElements(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            int count = 0;
            if (i > 0) {
                sum += nums[i - 1];
                count++;
            }
            if (i + 1 < n) {
                sum += nums[i + 1];
                count++;
            }
            if ((long) count * nums[i] >= sum) {
                ans++;
            }
        }
        return ans;
    }
}