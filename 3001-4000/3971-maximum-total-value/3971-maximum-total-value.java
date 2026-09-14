class Solution {
    public int maxTotalValue(int[] value, int[] decay, int m) {
        final long MOD = 1_000_000_007L;
        int n = value.length;

        int maxV = 0;
        for (int v : value) {
            maxV = Math.max(maxV, v);
        }

        long positiveCount = countGe(value, decay, 1, null);
        if (positiveCount <= m) {
            return (int) (sumGe(value, decay, 1) % MOD);
        }

        long K = m;

        int lo = 1, hi = maxV;
        while (lo < hi) {
            int mid = lo + (hi - lo + 1) / 2;

            if (countGe(value, decay, mid, K) >= K) {
                lo = mid;
            } else {
                hi = mid - 1;
            }
        }

        int L = lo;

        long cntGreater = countGe(value, decay, L + 1, null);
        long sumGreater = sumGe(value, decay, L + 1);

        long ans = sumGreater + (K - cntGreater) * L;

        return (int) (ans % MOD);
    }

    private long countGe(int[] value, int[] decay, int x, Long cap) {
        long total = 0;

        for (int i = 0; i < value.length; i++) {
            int a = value[i];
            int b = decay[i];

            if (a >= x) {
                total += (a - x) / b + 1;

                if (cap != null && total >= cap) {
                    return total;
                }
            }
        }

        return total;
    }

    private long sumGe(int[] value, int[] decay, int x) {
        long total = 0;

        for (int i = 0; i < value.length; i++) {
            long a = value[i];
            long b = decay[i];

            if (a >= x) {
                long c = (a - x) / b + 1;
                total += c * a - b * c * (c - 1) / 2;
            }
        }

        return total;
    }
}