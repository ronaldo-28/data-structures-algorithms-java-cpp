class Solution {
    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += distance(nums, i);
        }
        return res;
    }
    private int distance(int[] nums, int pos) {
        int count = 0;
        for (int num : nums) {
            count += ((num >> pos) & 1);
        }
        return count * (nums.length - count);
    }
}