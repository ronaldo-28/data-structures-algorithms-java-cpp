class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int max = 0;
        int len;

        for (int i = 0; i < nums.length - max; i++)
            if ((nums[i] & 1) == 0 && nums[i] <= threshold) {
                len = 1;
                while (
                        i + len < nums.length &&
                        nums[i + len] <= threshold &&
                        (nums[i + len - 1] & 1) != (nums[i + len] & 1)
                )
                    len++;

                if (max < len) max = len;
            }

        return max;
    }
}