class Solution {
    public int minMergedInteger(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length; i += 2) {
            int merged = nums[i];
            int second = nums[i + 1];

            int temp = second;
            while (temp > 0) {
                merged *= 10;
                temp /= 10;
            }

            merged += second;

            min = Math.min(min, merged);
        }

        return min;
    }
}