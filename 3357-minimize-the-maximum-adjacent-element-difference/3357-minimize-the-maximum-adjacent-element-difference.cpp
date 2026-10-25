class Solution {
public:
        int minDifference(vector<int>& A) {
        int n = A.size(), max_adj = 0, mina = INT_MAX, maxb = 0;
        for (int i = 0; i < n - 1; ++i) {
            int a = A[i], b = A[i + 1];
            if (a > 0 && b > 0) {
                max_adj = max(max_adj, abs(a - b));
            } else if (a > 0 || b > 0) {
                mina = min(mina, max(a, b));
                maxb = max(maxb, max(a, b));
            }
        }

        int res = 0, min_2r = (maxb - mina + 2) / 3 * 2;
        for (int i = 0; i < n; ++i) {
            if ((i > 0 && A[i - 1] == -1) || A[i] > 0) continue;
            int j = i;
            while (j < n && A[j] == -1) {
                j++;
            }
            int a = INT_MAX, b = 0;
            if (i > 0) {
                a = min(a, A[i - 1]);
                b = max(b, A[i - 1]);
            }
            if (j < n) {
                a = min(a, A[j]);
                b = max(b, A[j]);
            }
            if (j - i == 1) {
                res = max(res, min(maxb - a, b - mina));
            } else {
                res = max(res, min({maxb - a, b - mina, min_2r}));
            }
        }
        return max(max_adj, (res + 1) / 2);
        }
};