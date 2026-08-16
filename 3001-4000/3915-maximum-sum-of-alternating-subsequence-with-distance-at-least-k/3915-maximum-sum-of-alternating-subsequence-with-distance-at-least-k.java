class Solution {
    static class Fenwick {
        int n;
        long[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
            Arrays.fill(bit, Long.MIN_VALUE);
        }

        void update(int idx, long val) {
            for (; idx <= n; idx += idx & -idx) {
                bit[idx] = Math.max(bit[idx], val);
            }
        }

        long query(int idx) {
            long res = Long.MIN_VALUE;
            for (; idx > 0; idx -= idx & -idx) {
                res = Math.max(res, bit[idx]);
            }
            return res;
        }
    }

    public long maxAlternatingSum(int[] nums, int k) {
        int n = nums.length;
        int MAXV = 100000;

        long[] up = new long[n];
        long[] down = new long[n];
        Arrays.fill(up, Long.MIN_VALUE);
        Arrays.fill(down, Long.MIN_VALUE);

        Fenwick pref = new Fenwick(MAXV);
        Fenwick suff = new Fenwick(MAXV);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (i - k >= 0) {
                int j = i - k;

                long candUp = nums[j];
                if (down[j] != Long.MIN_VALUE) candUp = Math.max(candUp, down[j]);
                pref.update(nums[j], candUp);

                long candDown = nums[j];
                if (up[j] != Long.MIN_VALUE) candDown = Math.max(candDown, up[j]);
                int rev = MAXV - nums[j] + 1;
                suff.update(rev, candDown);
            }

            long bestLess = pref.query(nums[i] - 1);
            if (bestLess != Long.MIN_VALUE) up[i] = bestLess + nums[i];

            int revCur = MAXV - nums[i] + 1;
            long bestGreater = suff.query(revCur - 1);
            if (bestGreater != Long.MIN_VALUE) down[i] = bestGreater + nums[i];

            ans = Math.max(ans, nums[i]);
            if (up[i] != Long.MIN_VALUE) ans = Math.max(ans, up[i]);
            if (down[i] != Long.MIN_VALUE) ans = Math.max(ans, down[i]);
        }

        return ans;
    }
}