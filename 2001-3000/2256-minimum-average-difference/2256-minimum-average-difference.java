class Solution {
    public int minimumAverageDifference(int[] nums) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }

        long left = 0;
        long min = Integer.MAX_VALUE;
        int ind = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            left += nums[i];
            long right = sum - left;
            long diff = Math.abs(left / (i + 1) - right / (nums.length - i - 1));
            if (diff < min) {
                if (diff == 0) return i; 
                min = diff;
                ind = i;
            }
        }

        if (sum / nums.length < min) return nums.length - 1;

        return ind;
    }
}