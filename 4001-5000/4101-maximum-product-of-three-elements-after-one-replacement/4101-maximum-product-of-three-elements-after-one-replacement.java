class Solution {
    public long maxProduct(int[] nums) {
        Arrays.sort(nums);
        long first,second;

        if (Math.abs(nums[0]) > Math.abs(nums[nums.length - 1])) {
            first = nums[0];
            if (Math.abs(nums[nums.length - 1]) > Math.abs(nums[1])) {
                second = nums[nums.length - 1];
            } else {
                second = nums[1];
            }
        } else {
            first = nums[nums.length - 1];
            if (Math.abs(nums[0]) > Math.abs(nums[nums.length - 2])) {
                second = nums[0];
            } else {
                second = nums[nums.length - 2];
            }
        }

        if (first * second > 0) {
            return first * second * 100000;
        } else {
            return first * second * -100000;
        }
    }
}