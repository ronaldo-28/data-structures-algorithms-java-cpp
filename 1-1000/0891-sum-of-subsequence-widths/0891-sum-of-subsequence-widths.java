

class Solution {
    public int sumSubseqWidths(int[] nums) {
        final long MOD = 1000000007L;
        int n = nums.length;
        int maxV = 0;
        for (int v : nums) if (v > maxV) maxV = v;
        int R = maxV + 1;
        int[] freq = new int[R];
        for (int v : nums) freq[v]++;
        int[] sorted = new int[n];
        int idx = 0;
        for (int val = 0; val < R; val++) {
            int f = freq[val];
            while (f-- > 0) sorted[idx++] = val;
        }
        long[] pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) pow2[i] = (pow2[i - 1] * 2) % MOD;
        long res = 0;
        for (int i = 0; i < n; i++) {
            long add = pow2[i];
            long sub = pow2[n - 1 - i];
            long diff = (add - sub) % MOD;
            if (diff < 0) diff += MOD;
            res = (res + (sorted[i] * diff) % MOD) % MOD;
        }
        return (int) res;
    }
}