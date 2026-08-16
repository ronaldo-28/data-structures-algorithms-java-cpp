class Solution {
    public static long findScore(final int[] nums) {
        final int n = nums.length;
        long s = 0;
        for (int j = 0; j < n; j += 2) {
            long odd = 0, even = 0;
            for (int v = nums[j], w; j + 1 < n && nums[j] > (w = nums[j + 1]); j++, v = w)
                if ((j & 1) == 1) odd += v; else even += v;
            s += ((j & 1) == 1 ? odd : even) + nums[j];
        }
        return s;
    }
}