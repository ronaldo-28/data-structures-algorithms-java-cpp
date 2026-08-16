class Solution {
    public long findTheArrayConcVal(int[] nums) {
        long concat = 0;

        int len = nums.length;
        int i = 0;
        int j = len - 1;

        while (i < j) {

            int right = nums[j--];

            concat += nums[i++] * getMultiplier(right) + right;
        }

        if ((len & 1) == 1) {
            concat += nums[i];
        }

        return concat;
    }

    private int getMultiplier(int num) {

        int multiplier = 1;

        while (num > 0) {
            num /= 10;
            multiplier *= 10;
        }

        return multiplier;
    }
}