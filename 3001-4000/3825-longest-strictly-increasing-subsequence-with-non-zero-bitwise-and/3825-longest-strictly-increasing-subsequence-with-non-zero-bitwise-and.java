class Solution {
    private int lis(int[] nums, int n) {
        int[] tails = new int[n];
        int sz = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int l = 0, r = sz;
            while (l < r) {
                int m = (l + r) >>> 1;
                if (tails[m] >= x) 
                    r = m;
                else 
                    l = m + 1;
            }
            tails[l] = x;
            if (l == sz) 
                sz++;
        }
        return sz;
    }

    public int longestSubsequence(int[] nums) {
        final int MAXBITS = 32;
        int result = 0;

        int n = nums.length;
        int[] buf = new int[n];

        for (int bit = 0; bit < MAXBITS; bit++) {
            int m = 0;
            for (int x : nums) {
                if (((x >> bit) & 1) != 0) 
                    buf[m++] = x;
            }
            result = Math.max(result, lis(buf, m));
        }
        return result;
    }
}