class Solution {

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {

        ArrayList<int[]> list = new ArrayList<>();
        int m = queries.length, n = nums1.length;
        int[] output = new int[m];
        int[][] pairs = new int[n][];

        // Remove unneeded pairs and sort the element in ascendin order by num1 values 
        for (int i = 0; i < n; i++) pairs[i] = new int[] {nums1[i], nums2[i]};
        Arrays.sort(pairs, (p1, p2) -> p1[1] == p2[1] ? p2[0] - p1[0] : p2[1] - p1[1]);
        for (int i = 0, j = 0; i < n; i++) if (j < pairs[i][0]) {
            j = pairs[i][0];
            list.add(pairs[i]);
        }

        // Build the binary indexed tree
        n = list.size();
        int[] tree = new int[n << 1];
        for (int i = n; i < n << 1; i++) tree[i] = list.get(i - n)[0] + list.get(i - n)[1];
        for (int i = n - 1; i > 0; i--) tree[i] = Math.max(tree[i << 1], tree[i << 1 | 1]);

        for (int i = 0; i < m; i++) {

            // find boundaries using binary search
            int l = leftBoundary(list, queries[i][0]), r = rightBoundary(queries[i][1], list);

            // compute maximum from the 2 boundaries
            output[i] = l > r ? -1 : findMaximum(l, n, r, tree);
        }
        return output;
    }

    private int leftBoundary(ArrayList<int[]> list, int p) {
        int output = 0;
        for (int r = list.size(); output < r;) {
            int m = (output + r) >> 1;
            if (list.get(m)[0] < p) output = m + 1;
            else r = m;
        }
        return output;
    }

    private int rightBoundary(int p, ArrayList<int[]> list) {
        int output = 0;
        for (int r = list.size(); output < r;) {
            int m = output + r >> 1;
            if (list.get(m)[1] < p) r = m;
            else output = m + 1;
        }
        return output - 1;
    }

    private int findMaximum(int l, int n, int r, int[] tree) {
        int output = 0;
        for (l += n, r += n; l <= r; l >>= 1, r >>= 1) {
            if ((l & 1) == 1) output = Math.max(output, tree[l++]);
            if ((r & 1) == 0) output = Math.max(output, tree[r--]);
        }
        return output;
    }

}