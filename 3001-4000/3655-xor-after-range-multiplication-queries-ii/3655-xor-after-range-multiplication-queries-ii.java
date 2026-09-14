import java.util.*;

public class Solution {
    private final long MOD = 1000000007L;

    private long multiply(long x, long y) {
        return ((x % MOD) * (y % MOD)) % MOD;
    }

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }
        return res;
    }

    private long inverse(long num) {
        return power(num, MOD - 2);
    }

    private void processLargeStep(int[] arr, int left, int right, int step, int val) {
        for (int i = left; i <= right; i += step) {
            arr[i] = (int) multiply(arr[i], val);
        }
    }

    private void processSmallStep(int[][] buf, int step, int left, int right, int val, int size) {
        buf[step][left] = (int) multiply(buf[step][left], val);
        for (int i = right + 1; i < size; i++) {
            if (i % step == left % step) {
                buf[step][i] = (int) multiply(buf[step][i], inverse(val));
                break;
            }
        }
    }

    private void finalizeUpdates(int[] arr, int[][] buf, int size, int bound) {
        for (int step = 1; step < bound; step++) {
            for (int start = 0; start < step; start++) {
                long carry = 1;
                for (int i = start; i < size; i += step) {
                    carry = multiply(carry, buf[step][i]);
                    arr[i] = (int) multiply(arr[i], carry);
                }
            }
        }
    }

    private int computeXor(int[] arr) {
        int ans = 0;
        for (int val : arr) {
            ans ^= val;
        }
        return ans;
    }

    public int xorAfterQueries(int[] arr, int[][] queries) {
        int lim = 300;
        int n = arr.length;
        int[][] buf = new int[lim][n];
        for (int[] row : buf) Arrays.fill(row, 1);

        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];
            if (k >= lim) {
                processLargeStep(arr, l, r, k, v);
            } else {
                processSmallStep(buf, k, l, r, v, n);
            }
        }

        finalizeUpdates(arr, buf, n, lim);
        return computeXor(arr);
    }
}