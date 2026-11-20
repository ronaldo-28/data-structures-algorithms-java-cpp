class Solution {
    private static final int[] cache = new int[60];

    static {
        for (int i = 2; i < cache.length; i++) {
            cache[i] = cache[Integer.bitCount(i)] + 1;
        }
    }

    public int[] popcountDepth(long[] nums, long[][] queries) {
        SumFenwickTree[] fenwickTrees = new SumFenwickTree[6];
        for (int i = 0; i < fenwickTrees.length; i++) {
            fenwickTrees[i] = new SumFenwickTree(nums.length);
        }

        for (int i = 0; i < nums.length; i++) {
            nums[i] = this.popCountDepth(nums[i]);
            fenwickTrees[(int) nums[i]].increment(i);
        }

        int size = 0;
        int[] result = new int[queries.length];

        for (long[] query : queries) {
            if (query[0] == 1) {
                SumFenwickTree fenwickTree = fenwickTrees[(int) query[3]];
                result[size++] = fenwickTree.sum((int) (query[2] + 1)) - fenwickTree.sum((int) query[1]);

            } else {
                int i = (int) query[1];

                int oldDepth = (int) nums[i];
                int newDepth = (int) (nums[i] = this.popCountDepth(query[2]));

                if (oldDepth != newDepth) {
                    fenwickTrees[oldDepth].decrement(i);
                    fenwickTrees[newDepth].increment(i);
                }
            }
        }

        return Arrays.copyOf(result, size);
    }

    private int popCountDepth(long num) {
        return num == 1 ? 0 : cache[Long.bitCount(num)] + 1;
    }

    private static class SumFenwickTree {
        private final int[] tree;

        public SumFenwickTree(int n) {
            tree = new int[n + 1];
        }

        public void increment(int i) {
            for (i++; i < tree.length; i += i & -i) {
                tree[i]++;
            }
        }

        public void decrement(int i) {
            for (i++; i < tree.length; i += i & -i) {
                tree[i]--;
            }
        }

        public int sum(int i) {
            int sum = 0;

            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }

            return sum;
        }
    }
}