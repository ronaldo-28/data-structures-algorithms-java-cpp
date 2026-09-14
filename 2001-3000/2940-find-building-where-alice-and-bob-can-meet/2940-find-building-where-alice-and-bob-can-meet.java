class Solution {
    public int[] leftmostBuildingQueries(int[] h, int[][] q) {
        int n = h.length, m = q.length;
        int[] r = new int[m], hd = new int[n], nx = new int[m], s = new int[n];
        for (int i = 0; i < n; i++) hd[i] = -1;
        for (int i = 0; i < m; i++) {
            int a = q[i][0], b = q[i][1];
            if (a > b) {
                int tmp = a; a = b; b = tmp;
            }
            if (a == b || h[a] < h[b]) {
                r[i] = b;
            } else {
                r[i] = -1;
                nx[i] = hd[b];
                hd[b] = i;
            }
        }
        int t = -1;
        for (int i = n - 1; i >= 0; i--) {
            int j = hd[i];
            while (j != -1) {
                int a = q[j][0], b = q[j][1];
                int u = a < b ? a : b;
                int v = h[u];
                int l = 0, hi = t, res = -1;
                while (l <= hi) {
                    int mid = (l + hi) >>> 1;
                    if (h[s[mid]] > v) {
                        res = s[mid];
                        l = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
                r[j] = res;
                j = nx[j];
            }
            while (t >= 0 && h[s[t]] <= h[i]) t--;
            s[++t] = i;
        }
        return r;
    }
}