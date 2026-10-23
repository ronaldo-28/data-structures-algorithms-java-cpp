class Solution {
    static final int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        long[][] m = new long[26][26];
        for (int c = 0; c < 26; c++) {
            for (int j = 1; j <= nums.get(c); j++) {
                m[(c + j) % 26][c]++;
            }
        }
        long[] v = new long[26];
        for (char ch : s.toCharArray()) {
            v[ch - 'a']++;
        }
        while (t > 0) {
            if ((t & 1) == 1) {
                v = apply(m, v);
            }
            m = multiply(m, m);
            t >>= 1;
        }
        long ans = 0;
        for (long x : v) {
            ans = (ans + x) % MOD;
        }
        return (int) ans;
    }

    long[] apply(long[][] m, long[] v) {
        long[] r = new long[26];
        for (int i = 0; i < 26; i++) {
            long sum = 0;
            for (int j = 0; j < 26; j++) {
                sum = (sum + m[i][j] * v[j]) % MOD;
            }
            r[i] = sum;
        }
        return r;
    }

    long[][] multiply(long[][] a, long[][] b) {
        long[][] r = new long[26][26];
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < 26; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < 26; j++) {
                    r[i][j] = (r[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return r;
    }
}