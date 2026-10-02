//https://leetcode.com/problems/find-all-possible-stable-binary-arrays-i/solutions/7635882/combinatorics-stars-bars-inclusion-exclu-x7rm
class Solution {

    private static final long MOD = 1_000_000_007L;

    public int numberOfStableArrays(int zero, int one, int limit) {

        int maxN = zero + one;

        /*
         * Precompute factorials and inverse factorials.
         * They are used to calculate combinations:
         *
         * C(n, k) = n! / (k! * (n-k)!)
         */
        long[] factorial = new long[maxN + 1];
        long[] inverseFactorial = new long[maxN + 1];

        factorial[0] = 1;

        for (int i = 1; i <= maxN; i++) {
            factorial[i] =
                    factorial[i - 1] * i % MOD;
        }

        /*
         * Fermat's Little Theorem:
         *
         * a^(-1) = a^(MOD - 2) mod MOD
         */
        inverseFactorial[maxN] =
                power(factorial[maxN], MOD - 2);

        for (int i = maxN - 1; i >= 0; i--) {
            inverseFactorial[i] =
                    inverseFactorial[i + 1] * (i + 1) % MOD;
        }

        /*
         * Maximum number of groups of zeroes that can be
         * formed while still allowing the one-groups to
         * separate them.
         */
        int maxGroups = Math.min(zero, one + 1);

        /*
         * fOne[k] = number of ways to divide all 'one'
         * elements into exactly k non-empty groups where
         * every group has size <= limit.
         */
        long[] waysForOneGroups = new long[maxGroups + 2];

        for (int groupCount = 1;
             groupCount <= maxGroups + 1;
             groupCount++) {

            waysForOneGroups[groupCount] =
                    calculateGroups(
                            one,
                            groupCount,
                            limit,
                            factorial,
                            inverseFactorial
                    );
        }

        long answer = 0;

        /*
         * Consider the number of groups of zeroes.
         */
        for (int zeroGroups = 1;
             zeroGroups <= maxGroups;
             zeroGroups++) {

            long waysForZeroGroups =
                    calculateGroups(
                            zero,
                            zeroGroups,
                            limit,
                            factorial,
                            inverseFactorial
                    );

            if (waysForZeroGroups == 0) {
                continue;
            }

            /*
             * If there are k groups of zeroes, then the
             * number of possible groups of ones is:
             *
             * k - 1, k, or k + 1
             *
             * with the middle case having two possible
             * arrangements.
             */
            long waysForOneGroupsCombination =
                    (
                        waysForOneGroups[zeroGroups - 1]
                        + 2L * waysForOneGroups[zeroGroups]
                        + waysForOneGroups[zeroGroups + 1]
                    ) % MOD;

            answer =
                    (answer
                     + waysForZeroGroups
                     * waysForOneGroupsCombination) % MOD;
        }

        return (int) answer;
    }

    /*
     * Calculates:
     *
     * F(N, K, L)
     *
     * = number of ways to divide N elements into K
     *   non-empty groups, with every group having
     *   size at most L.
     *
     * Inclusion-exclusion is used to remove arrangements
     * where one or more groups have size greater than L.
     */
    private long calculateGroups(
            int totalElements,
            int groupCount,
            int limit,
            long[] factorial,
            long[] inverseFactorial) {

        /*
         * It is impossible to create K non-empty groups
         * from fewer than K elements.
         */
        if (groupCount <= 0 || groupCount > totalElements) {
            return 0;
        }

        long answer = 0;

        int maxExcludedGroups =
                (totalElements - groupCount) / limit;

        for (int excludedGroups = 0;
             excludedGroups <= maxExcludedGroups;
             excludedGroups++) {

            /*
             * Choose which groups violate the size limit.
             */
            long chooseGroups =
                    combination(
                            groupCount,
                            excludedGroups,
                            factorial,
                            inverseFactorial
                    );

            /*
             * Number of ways after forcing the selected
             * groups to contain at least limit + 1 elements.
             */
            int remainingElements =
                    totalElements
                    - excludedGroups * limit
                    - 1;

            long arrangeRemaining =
                    combination(
                            remainingElements,
                            groupCount - 1,
                            factorial,
                            inverseFactorial
                    );

            long term =
                    chooseGroups
                    * arrangeRemaining % MOD;

            /*
             * Inclusion-exclusion:
             *
             * even number of violations -> add
             * odd number of violations  -> subtract
             */
            if (excludedGroups % 2 == 0) {
                answer = (answer + term) % MOD;
            } else {
                answer = (answer - term + MOD) % MOD;
            }
        }

        return answer;
    }

    /*
     * Calculate C(n, k) modulo MOD.
     */
    private long combination(
            int n,
            int k,
            long[] factorial,
            long[] inverseFactorial) {

        if (k < 0 || k > n) {
            return 0;
        }

        return factorial[n]
                * inverseFactorial[k] % MOD
                * inverseFactorial[n - k] % MOD;
    }

    /*
     * Fast modular exponentiation.
     */
    private long power(long base, long exponent) {

        long result = 1;
        base %= MOD;

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