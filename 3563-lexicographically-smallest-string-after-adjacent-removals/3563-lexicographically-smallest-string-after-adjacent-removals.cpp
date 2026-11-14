#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    string lexicographicallySmallestString(string s) {
        int n = s.size();

        auto adj = [](char a, char b) {
            int d = abs(a - b);
            return d == 1 || d == 25;
        };

        // can[i][j]: s[i..j) can be fully removed
        vector<vector<char>> can(n + 2, vector<char>(n + 2, 0));
        for (int i = 0; i <= n; i++) can[i][i] = 1;

        for (int len = 2; len <= n; len += 2) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k += 2) {
                    if (adj(s[i], s[k]) && can[i + 1][k] && can[k + 1][j]) {
                        can[i][j] = 1;
                        break;
                    }
                }
            }
        }

        // dp[i]: smallest string obtainable from suffix s[i..]
        vector<string> dp(n + 1);
        dp[n] = "";
        for (int i = n - 1; i >= 0; i--) {
            string best = s[i] + dp[i + 1];
            for (int j = i + 2; j <= n; j += 2) {
                if (can[i][j] && dp[j] < best) best = dp[j];
            }
            dp[i] = best;
        }

        return dp[0];
    }
};