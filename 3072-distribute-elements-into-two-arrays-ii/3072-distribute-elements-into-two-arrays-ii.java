class Solution {

    static void update(int i, int[] b) {
        for (; i < b.length; i += i & -i)
            ++b[i];
    }

    static int sum(int i, int[] b) {
        int s = 0;
        while (i > 0) {
            s += b[i];
            i &= i - 1;
        }
        return s;
    }

    public int[] resultArray(int[] nums) {
        int n = nums.length;
        if (n <= 2) return nums;

        int[] b1 = new int[n + 1];
        int[] b2 = new int[n + 1];
        int[] a1 = new int[n];
        int[] a2 = new int[n];

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int m = 1;
        for (int i = 1; i < n; i++)
            if (sorted[i] != sorted[m - 1])
                sorted[m++] = sorted[i];


        int size = 1;
        while (size < m * 2)
            size <<= 1;

        int[] keys = new int[size];
        int[] vals = new int[size];

        for (int i = 0; i < m; i++) {
            int x = sorted[i];
            int h = (x ^ (x >>> 16)) * 0x9E3779B9;
            int p = h & (size - 1);

            while (vals[p] != 0 && keys[p] != x)
                p = (p + 1) & (size - 1);

            keys[p] = x;
            vals[p] = i + 1;
        }

        int id1 = 1, id2 = 1;

        a1[0] = nums[0];
        a2[0] = nums[1];

        int r = getRank(nums[0], keys, vals);
        update(r, b1);

        r = getRank(nums[1], keys, vals);
        update(r, b2);

        for (int i = 2; i < n; i++) {
            r = getRank(nums[i], keys, vals);

            int d1 = id1 - sum(r, b1);
            int d2 = id2 - sum(r, b2);

            if (d1 > d2 || (d1 == d2 && id1 <= id2)) {
                a1[id1++] = nums[i];
                update(r, b1);
            } else {
                a2[id2++] = nums[i];
                update(r, b2);
            }
        }

        System.arraycopy(a1, 0, nums, 0, id1);
        System.arraycopy(a2, 0, nums, id1, id2);

        return nums;
    }

    static int getRank(int x, int[] keys, int[] vals) {
        int mask = keys.length - 1;
        int h = (x ^ (x >>> 16)) * 0x9E3779B9;
        int p = h & mask;

        while (keys[p] != x)
            p = (p + 1) & mask;

        return vals[p];
    }
}