import java.util.Arrays;

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int minMaxSums(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;

        long[] factorial = new long[n + 1];
        long[] invFactorial = new long[n + 1];
        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i % MOD;
        }

        invFactorial[n] = modPow(factorial[n], MOD - 2);
        for (int i = n; i > 0; i--) {
            invFactorial[i - 1] = invFactorial[i] * i % MOD;
        }

        int maxChosen = k - 1;
        long[] ways = new long[n];
        ways[0] = 1;

        for (int m = 1; m < n; m++) {
            long excluded = 0;
            if (m - 1 >= maxChosen) {
                excluded = factorial[m - 1] * invFactorial[maxChosen] % MOD
                        * invFactorial[m - 1 - maxChosen] % MOD;
            }

            ways[m] = (2 * ways[m - 1] - excluded + MOD) % MOD;
        }

        long answer = 0;
        for (int i = 0; i < n; i++) {
            long count = (ways[i] + ways[n - 1 - i]) % MOD;
            answer = (answer + nums[i] * count) % MOD;
        }

        return (int) answer;
    }

    private long modPow(long base, long exponent) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % MOD;
            }
            base = base * base % MOD;
            exponent >>= 1;
        }

        return result;
    }
}