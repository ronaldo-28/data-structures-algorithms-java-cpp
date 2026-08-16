#include <vector>
#include <climits>
using namespace std;

class Solution {
public:
    int mergeStones(vector<int>& stones, int K) {
        int n = stones.size();
        if ((n - 1) % (K - 1) != 0) return -1; // impossible to merge into one pile

        // prefix sums for quick interval sum
        vector<int> prefix(n + 1, 0);
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stones[i];
        }

        // dp[i][j] = min cost to merge stones[i..j]
        vector<vector<int>> dp(n, vector<int>(n, 0));

        for (int len = K; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = INT_MAX;
                // try splitting into groups of (K-1)
                for (int m = i; m < j; m += K - 1) {
                    dp[i][j] = min(dp[i][j], dp[i][m] + dp[m + 1][j]);
                }
                // if we can merge into one pile, add total sum
                if ((len - 1) % (K - 1) == 0) {
                    dp[i][j] += prefix[j + 1] - prefix[i];
                }
            }
        }

        return dp[0][n - 1];
    }
};