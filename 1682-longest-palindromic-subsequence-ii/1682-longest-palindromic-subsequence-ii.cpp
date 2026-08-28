class Solution {
public:
    int longestPalindromeSubseq(string s) {
        int n = s.size();

        vector<vector<int>> dp(n, vector<int>(n, 0));
        vector<vector<char>> border(n, vector<char>(n));

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s[i] == s[j] && (j - i < 2 || s[i] != border[i + 1][j - 1])) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                    border[i][j] = s[i];
                } else if (dp[i][j - 1] > dp[i + 1][j]) {
                    dp[i][j] = dp[i][j - 1];
                    border[i][j] = border[i][j - 1];
                } else {
                    dp[i][j] = dp[i + 1][j];
                    border[i][j] = border[i + 1][j];
                }
            }
        }
        return dp[0][n - 1];
    }
};