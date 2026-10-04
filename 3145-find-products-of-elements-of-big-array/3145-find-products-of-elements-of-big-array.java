class Solution {

    public int[] findProductsOfElements(long[][] queries) {

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long from = queries[i][0];
            long to = queries[i][1];
            long mod = queries[i][2];

            long right = sumE(to + 1);
            long left = sumE(from);

            long exponent = right - left;

            answer[i] = power(2, exponent, mod);
        }

        return answer;
    }

    /*
     * Returns the sum of exponents of 2 in the first k
     * elements of big_nums.
     */
    private long sumE(long k) {

        long result = 0;

        long n = 0;
        long count = 0;
        long sum = 0;

        /*
         * Process powers of two from large to small.
         */
        for (long i = 63L - Long.numberOfLeadingZeros(k + 1);
             i > 0;
             i--) {

            /*
             * Number of elements contributed by the
             * current bit position.
             */
            long countElements =
                (count << i)
                + (i << (i - 1));

            if (countElements <= k) {

                k -= countElements;

                /*
                 * Add the total exponent contributed
                 * by these elements.
                 */
                result +=
                    (sum << i)
                    + ((i * (i - 1) / 2) << (i - 1));

                sum += i;
                count++;

                n |= 1L << i;
            }
        }

        /*
         * Handle the remaining group.
         */
        if (count <= k) {

            k -= count;

            result += sum;

            n++;
        }

        /*
         * Process the remaining individual elements.
         */
        while (k-- > 0) {

            result += Long.numberOfTrailingZeros(n);

            n &= n - 1;
        }

        return result;
    }

    private int power(long base, long exponent, long mod) {

        if (mod == 1) {
            return 0;
        }

        long result = 1 % mod;

        base %= mod;

        while (exponent > 0) {

            if ((exponent & 1) == 1) {
                result = (result * base) % mod;
            }

            base = (base * base) % mod;

            exponent >>= 1;
        }

        return (int) result;
    }
}