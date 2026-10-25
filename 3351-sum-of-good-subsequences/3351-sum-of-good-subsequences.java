class Solution {
    public int sumOfGoodSubsequences(int[] nums) {
        final long MOD = 1_000_000_007L;
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        long[] cnt = new long[max + 2];
        long[] sum = new long[max + 2];
        long ans = 0;
        for (int x : nums) {
            long c = 1;
            long s = x;
            if (x > 0) {
                c += cnt[x - 1];
                s += sum[x - 1] + x * cnt[x - 1] % MOD;
            }
            c += cnt[x + 1];
            s += sum[x + 1] + x * cnt[x + 1] % MOD;
            c %= MOD;
            s %= MOD;
            cnt[x] = (cnt[x] + c) % MOD;
            sum[x] = (sum[x] + s) % MOD;
            ans = (ans + s) % MOD;
        }
        return (int) ans;
    }
}