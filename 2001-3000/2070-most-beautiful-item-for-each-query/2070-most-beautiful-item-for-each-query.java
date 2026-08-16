class Solution {
    public int[] maximumBeauty(int[][] items, int[] queries) {
        // sort items and for queries we can go through and use BS on items

        Arrays.sort(items, (a, b) -> {
            return b[1] - a[1];
        });

        int[] res = new int[queries.length];
        for (int i = 0; i < res.length; i++) {
            for (int[] item : items) {
                if (item[0] <= queries[i]) {
                    res[i] = item[1];
                    break;
                }
            }
        }

        return res;
    }
}