class Solution {
    public long popcountDepth(long n, int k) {
        if (k == 0) {
            return 1;
        }

        long ans = 0;

        for (int ones = 1; ones <= 60; ones++) {
            if (getDepth(ones) == k - 1) {
                ans += countOnes(n, ones);
            }
        }

        if (k == 1) {
            ans--;
        }

        return ans;
    }

    private int getDepth(long x) {
        int depth = 0;

        while (x != 1) {
            x = Long.bitCount(x);
            depth++;
        }

        return depth;
    }

    private long countOnes(long n, int target) {
        long result = 0;
        int remaining = Long.bitCount(n);

        for (int pos = 60; pos >= 0; pos--) {
            if (((n >> pos) & 1L) != 0) {
                if (target >= 0 && target <= pos) {
                    result += combinations(pos, target);
                }

                target--;

                if (target < 0) {
                    break;
                }
            }
        }

        if (target == 0) {
            result++;
        }

        return result;
    }

    private long combinations(int n, int r) {
        if (r < 0 || r > n) {
            return 0;
        }

        r = Math.min(r, n - r);

        long result = 1;

        for (int i = 1; i <= r; i++) {
            result = result * (n - r + i) / i;
        }

        return result;
    }
}