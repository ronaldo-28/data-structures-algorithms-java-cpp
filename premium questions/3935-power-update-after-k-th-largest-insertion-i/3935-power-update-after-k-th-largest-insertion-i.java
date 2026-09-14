import java.util.*;

class Solution {

    private static final long MOD = 1_000_000_007L;

    private long powMOD(int p, int pow) {
        long base = p;
        long result = 1;

        while (pow > 0) {
            if ((pow & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            pow >>= 1;
        }

        return result;
    }

    public List<Integer> powerUpdate(
            int[] nums,
            int p,
            int[][] queries) {

        int n = nums.length;
        int q = queries.length;

        /*
         * Collect all values that can ever appear in the multiset.
         */
        int[] values = new int[n + q];

        int idx = 0;

        for (int x : nums) {
            values[idx++] = x;
        }

        for (int[] query : queries) {
            values[idx++] = query[0];
        }

        Arrays.sort(values);

        /*
         * Coordinate compression.
         */
        int unique = 0;

        for (int i = 0; i < values.length; i++) {
            if (i == 0 || values[i] != values[i - 1]) {
                values[unique++] = values[i];
            }
        }

        /*
         * Fenwick tree stores frequencies.
         *
         * index 1 = smallest value
         * index unique = largest value
         */
        Fenwick bit = new Fenwick(unique);

        /*
         * Insert initial nums.
         */
        for (int x : nums) {
            int pos = lowerBound(values, unique, x) + 1;
            bit.add(pos, 1);
        }

        List<Integer> ans = new ArrayList<>(q);

        /*
         * rank = zero-based position from the largest element.
         *
         * Equivalent to the iterator position used by the
         * original C++ multiset solution.
         */
        int rank = -1;
        int prevK = 0;

        for (int[] query : queries) {

            int x = query[0];
            int currK = query[1];

            /*
             * Insert q[0].
             */
            int pos = lowerBound(values, unique, x) + 1;
            bit.add(pos, 1);

            /*
             * Same iterator-position logic as the optimized
             * interpretation of the original C++ code.
             */
            rank += currK - prevK;

            /*
             * Find rank-th element from the largest.
             *
             * rank = 0 -> largest
             * rank = 1 -> second largest
             * ...
             */
            int totalCount = bit.sum(unique);

            int kthSmallest = totalCount - rank;

            int compressedIndex = bit.findByOrder(kthSmallest);

            int selectedValue = values[compressedIndex - 1];

            /*
             * p = p ^ selectedValue (mod MOD)
             */
            p = (int) powMOD(p, selectedValue);

            ans.add(p);

            prevK = currK;
        }

        return ans;
    }

    private int lowerBound(
            int[] arr,
            int size,
            int target) {

        int lo = 0;
        int hi = size;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }

    /*
     * Fenwick Tree / Binary Indexed Tree
     */
    static class Fenwick {

        private final int[] tree;
        private final int n;

        Fenwick(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        void add(int index, int delta) {
            while (index <= n) {
                tree[index] += delta;
                index += index & -index;
            }
        }

        int sum(int index) {
            int result = 0;

            while (index > 0) {
                result += tree[index];
                index -= index & -index;
            }

            return result;
        }

        /*
         * Returns the smallest Fenwick index whose prefix sum
         * is >= k.
         *
         * In other words: k-th smallest element.
         */
        int findByOrder(int k) {

            int index = 0;

            int highestPower = Integer.highestOneBit(n);

            for (int bitMask = highestPower;
                 bitMask != 0;
                 bitMask >>= 1) {

                int next = index + bitMask;

                if (next <= n && tree[next] < k) {
                    index = next;
                    k -= tree[next];
                }
            }

            return index + 1;
        }
    }
}
