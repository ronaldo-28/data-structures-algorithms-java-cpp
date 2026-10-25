class Solution {
        public int minDifference(int[] A) {
        int n = A.length, max_adj = 0, mina = Integer.MAX_VALUE, maxb = 0;
        for (int i = 0; i < n - 1; ++i) {
            int a = A[i], b = A[i + 1];
            if (a > 0 && b > 0) {
                max_adj = Math.max(max_adj, Math.abs(a - b));
            } else if (a > 0 || b > 0) {
                mina = Math.min(mina, Math.max(a, b));
                maxb = Math.max(maxb, Math.max(a, b));
            }
        }

        int res = 0, min_2r = (maxb - mina + 2) / 3 * 2;
        for (int i = 0; i < n; ++i) {
            if ((i > 0 && A[i - 1] == -1) || A[i] > 0) continue;
            int j = i;
            while (j < n && A[j] == -1) {
                j++;
            }
            int a = Integer.MAX_VALUE, b = 0;
            if (i > 0) {
                a = Math.min(a, A[i - 1]);
                b = Math.max(b, A[i - 1]);
            }
            if (j < n) {
                a = Math.min(a, A[j]);
                b = Math.max(b, A[j]);
            }
            if (j - i == 1) {
                res = Math.max(res, Math.min(maxb - a, b - mina));
            } else {
                res = Math.max(res, Math.min(maxb - a, Math.min(b - mina, min_2r)));
            }
        }
        return Math.max(max_adj, (res + 1) / 2);
    }
}