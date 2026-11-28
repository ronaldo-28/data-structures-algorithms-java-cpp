class Solution {
    public long countNoZeroPairs(long n) {
        // mask: bit 0 means a has ended; bit 1 means b has ended.
        // State index is mask * 2 + carry. Allocate both buffers only once.
        long[] dp = new long[8];
        long[] next = new long[8];
        dp[0] = 1;
        boolean firstDigit = true;

        while (n > 0) {
            int target = (int) (n % 10);
            n /= 10;
            java.util.Arrays.fill(next, 0L);

            for (int mask = 0; mask < 4; mask++) {
                for (int carry = 0; carry <= 1; carry++) {
                    long ways = dp[mask * 2 + carry];
                    if (ways == 0) {
                        continue;
                    }

                    for (int nextCarry = 0; nextCarry <= 1; nextCarry++) {
                        int sum = target + 10 * nextCarry - carry;

                        // Both digits are nonzero: neither number can have ended.
                        if (mask == 0) {
                            next[nextCarry] += ways * getTypes(sum);
                        }

                        // Zero padding is allowed only above the units column.
                        if (!firstDigit) {
                            if (sum >= 1 && sum <= 9) {
                                if ((mask & 1) == 0) { // a is nonzero, b is zero.
                                    next[(mask | 2) * 2 + nextCarry] += ways;
                                }
                                if ((mask & 2) == 0) { // a is zero, b is nonzero.
                                    next[(mask | 1) * 2 + nextCarry] += ways;
                                }
                            } else if (sum == 0) { // Both digits are zero.
                                next[6 + nextCarry] += ways;
                            }
                        }
                    }
                }
            }
            long[] previous = dp;
            dp = next;
            next = previous;
            firstDigit = false;
        }

        long result = 0;
        for (int mask = 0; mask < 4; mask++) {
            result += dp[mask * 2];
        }
        return result;
    }

    // Number of ordered pairs of digits in 1..9 whose sum equals sum.
    private int getTypes(int sum) {
        if (sum < 2 || sum > 18) {
            return 0;
        }
        return sum <= 10 ? sum - 1 : 19 - sum;
    }
}