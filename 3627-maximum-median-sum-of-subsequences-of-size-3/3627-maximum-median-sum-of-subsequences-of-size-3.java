class Solution {
    public long maximumMedianSum(int[] nums) {
        Arrays.sort(nums);
        long res = 0;
        int start = 0, end = nums.length - 1;
        while(start < end) {
            end--;
            res += nums[end];
            end--;
            start++;
        }
        return res;
    }
}