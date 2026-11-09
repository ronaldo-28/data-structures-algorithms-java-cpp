class Solution {

    public int uniqueXorTriplets(int[] nums) {

        /*
         * Find the maximum value so we know how many bits are needed.
         */
        int maxVal = 0;

        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        int size = 1;

        while (size <= maxVal) {
            size <<= 1;
        }

        /*
         * freq[x] = whether x exists in nums.
         *
         * We only care about existence because we need
         * UNIQUE XOR values, not the number of triplets.
         */
        long[] freq = new long[size];

        for (int x : nums) {
            freq[x] = 1;
        }

        /*
         * XOR convolution:
         *
         * Transform freq
         * cube every value
         * inverse transform
         *
         * A non-zero result means that value can be
         * obtained as a ^ b ^ c.
         */
        fwht(freq, false);

        for (int i = 0; i < size; i++) {
            freq[i] = freq[i] * freq[i] * freq[i];
        }

        fwht(freq, true);

        int answer = 0;

        for (int i = 0; i < size; i++) {
            if (freq[i] != 0) {
                answer++;
            }
        }

        return answer;
    }

    private void fwht(long[] a, boolean inverse) {

        int n = a.length;

        for (int len = 1; len < n; len <<= 1) {

            for (int i = 0; i < n; i += len << 1) {

                for (int j = 0; j < len; j++) {

                    long u = a[i + j];
                    long v = a[i + j + len];

                    a[i + j] = u + v;
                    a[i + j + len] = u - v;
                }
            }
        }

        if (inverse) {
            for (int i = 0; i < n; i++) {
                a[i] /= n;
            }
        }
    }
}