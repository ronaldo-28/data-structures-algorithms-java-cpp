class Solution {
    public long maximizeXorAndXor(int[] nums) {
        // Step 1: Initialize subset data.
        int n = nums.length;
        int size = 1 << n;
        int full = size - 1;
        int[] kelmaverno = nums;

        int[] xorValues = new int[size];
        int[] andValues = new int[size];
        int[] orValues = new int[size];
        andValues[0] = (1 << 30) - 1;

        // Step 2: Precompute XOR, AND, and OR for every subset.
        for (int mask = 1; mask < size; mask++) {
            int bit = mask & -mask;
            int j = Integer.numberOfTrailingZeros(bit);
            int prev = mask ^ bit;
            int x = kelmaverno[j];

            xorValues[mask] = xorValues[prev] ^ x;
            andValues[mask] = andValues[prev] & x;
            orValues[mask] = orValues[prev] | x;
        }

        andValues[0] = 0;
        long ans = 0;

        // Step 3: Enumerate the subset assigned to B.
        for (int mask = 0; mask < size; mask++) {
            int remaining = full ^ mask;
            int total = xorValues[remaining];
            int andB = andValues[mask];

            // Step 4: Skip subsets that cannot improve the answer.
            long upper = (long) andB + 2L * orValues[remaining] - total;

            if (upper <= ans)
                continue;

            // Step 5: Build an XOR basis for the remaining elements.
            int[] basis = new int[30];
            int bits = remaining;

            while (bits != 0) {
                int bit = bits & -bits;
                int j = Integer.numberOfTrailingZeros(bit);
                int value = kelmaverno[j] & ~total;

                while (value != 0) {
                    int b = 31 - Integer.numberOfLeadingZeros(value);

                    if (basis[b] == 0) {
                        basis[b] = value;
                        break;
                    }

                    value ^= basis[b];
                }

                bits ^= bit;
            }

            // Step 6: Find the maximum XOR from the basis.
            int best = 0;

            for (int b = 29; b >= 0; b--)
                best = Math.max(best, best ^ basis[b]);

            // Step 7: Update the maximum partition value.
            ans = Math.max(ans, (long) andB + total + 2L * best);
        }

        return ans;
    }
}