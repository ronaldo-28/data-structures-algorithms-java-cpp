
class Solution {
public:
    int maxScore(vector<vector<int>>& grid) {
        int n = grid.size();

        // rows[x] = bitmask of rows containing value x
        vector<int> rows(101, 0);

        for (int i = 0; i < n; i++) {
            for (int x : grid[i]) {
                rows[x] |= (1 << i);
            }
        }

        int dp[1 << 10] = {};

        for (int x = 1; x <= 100; x++) {
            if (rows[x] == 0) continue;

            int ndp[1 << 10];

            // Start with the option of NOT taking x.
            memcpy(ndp, dp, sizeof(dp));

            for (int mask = 0; mask < (1 << n); mask++) {
                // Try putting value x into every possible row.
                int available = rows[x] & (~mask);

                while (available) {
                    int bit = available & -available;
                    available -= bit;

                    int newMask = mask | bit;

                    ndp[newMask] = max(ndp[newMask], dp[mask] + x);
                }
            }

            memcpy(dp, ndp, sizeof(dp));
        }

        int ans = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            ans = max(ans, dp[mask]);
        }

        return ans;
    }
};
