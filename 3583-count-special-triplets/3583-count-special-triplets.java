class Solution {
    static int[] f = new int[100001];
    static int[] r = new int[100001];
    final int MOD = 1_000_000_007;
    public int specialTriplets(int[] nums) {
        for(int n: nums) {
            r[n]++;
        }
        int count = 0, t;
        for(int n: nums) {
            r[n]--;
            t = n << 1;
            if(t < f.length) {
            count = (count + (int)((1L * f[t] * r[t]) % MOD)) % MOD;
            }
            f[n]++;
        }
        for(int n: nums) {
            f[n] = 0;
        }
        return count;
    }
}