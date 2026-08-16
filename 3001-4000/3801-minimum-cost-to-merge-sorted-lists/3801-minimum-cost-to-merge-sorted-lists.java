class Solution {
    public long minMergeCost(int[][] lists) {
        int n = lists.length, m = n >> 1, u = 1 << n, half = (1 << m) - 1;
        int[][] sorted1 = sort(lists, 0, m), sorted2 = sort(lists, m, n - m);
        int[] median = new int[u];
        for (int i = 1; i < u; ++i) median[i] = search(sorted1[i & half], sorted2[i >> m]);
        long[] dp = new long[u];
        for (int i = 3; i < u; ++i) {
            if ((i & (i - 1)) == 0) continue;
            dp[i] = Long.MAX_VALUE;
            for (int j = i & (i - 1); j > (i ^ j); j = (j - 1) & i) {
                int k = i ^ j;
                dp[i] = Math.min(dp[i], dp[j] + dp[k] + Math.abs(median[j] - median[k]));
            }
            dp[i] += sorted1[i & half].length + sorted2[i >> m].length;
        }
        return dp[u - 1];
    }

    private static int[][] sort(int[][] lists, int start, int n) {
        int u = 1 << n;
        int[][] sorted = new int[u][];
        sorted[0] = new int[]{};
        for (int i = 0; i < n; ++i) {
            int highBit = 1 << i;
            for (int s = 0; s < highBit; ++s) {
                sorted[highBit | s] = merge(sorted[s], lists[start + i]);
            }
        }
        return sorted;
    }

    private static int search(int[] a, int[] b) {
        if (a.length > b.length) {
            int[] tmp = a;
            a = b;
            b = tmp;
        }
        int m = a.length, n = b.length, left = -1, right = m;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1, j = ((m + n + 1) >> 1) - mid - 2;
            if (a[mid] <= b[j + 1]) left = mid;
            else right = mid;
        }
        int i = left, j = ((m + n + 1) >> 1) - i - 2;
        int ai = i >= 0 ? a[i] : Integer.MIN_VALUE;
        int bj = j >= 0 ? b[j] : Integer.MIN_VALUE;
        return Math.max(ai, bj);
    }

    private static int[] merge(int[] a, int[] b) {
        int n = a.length, m = b.length;
        int[] res = new int[n + m];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (a[i] <= b[j]) res[k++] = a[i++];
            else res[k++] = b[j++];
        }
        while (i < n) res[k++] = a[i++];
        while (j < m) res[k++] = b[j++];
        return res;
    }
}