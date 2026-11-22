class Solution {
    public int minRange(int a, int b, int c) {
        int[] nums = {a, b, c};
        Arrays.sort(nums);
        
        int initialRange = nums[2] - nums[0];
        int oneOpRange = nums[1];
        
        return Math.min(initialRange, oneOpRange);
    }
}