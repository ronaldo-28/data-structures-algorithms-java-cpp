class Solution {
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i] - nums2[i];
            if (num < min) {
                min = num;
            }
            if (num > max) {
                max = num;
            }
        }

        int range = max - min + 1;
        long[] tree = new long[range  +1];

        long count = 0;
        for (int i = 0; i < nums1.length; i++) {
            int num = nums1[i] - nums2[i];
            count += query(tree, (num + diff > max ? max : num + diff) - min);
            update(tree, num - min);
        }

        return count;
    }

    void update(long[] tree, int i) {
        i++;
        while (i < tree.length) {
            tree[i]++;
            i += i & -i;
        }
    }

    long query(long[] tree, int i) {
        i++;
        long sum = 0;
        while (i > 0) {
            sum += tree[i];
            i -= i & -i;
        }
        return sum;
    }
}