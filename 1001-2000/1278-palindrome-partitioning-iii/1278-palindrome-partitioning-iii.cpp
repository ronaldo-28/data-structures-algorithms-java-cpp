class Solution {
public:
    int palindromePartition(string s, int k) {
        int n = s.size();
        static int cost[105][105];
        static int dp[105][105];
        memset(cost, 0, sizeof(cost));
        memset(dp, 0x3f, sizeof(dp));

        // cost[i][j] = min changes to make s[i..j] a palindrome
        for (int i = 0; i < n; i++) cost[i][i] = 0;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                cost[i][j] = cost[i + 1][j - 1] + (s[i] != s[j]);
            }
        }

        // dp[p][i] = min changes to split prefix s[0..i] into exactly p palindromes

        // base case: only 1 partition -> whole prefix is one palindrome
        for (int i = 0; i < n; i++)
            dp[1][i] = cost[0][i];

        // build partitions count from 2 to k
        for (int p = 2; p <= k; p++) {
            for (int i = 0; i < n; i++) {

                // try all possible positions for the last cut
                // last palindrome will be s[j+1 .. i]
                // prefix s[0 .. j] must already be split into (p-1) palindromes
                for (int j = p - 2; j < i; j++) { 
                    dp[p][i] = min(dp[p][i],
                                   dp[p - 1][j] + cost[j + 1][i]);
                }
            }
        }

        // answer: split full string s[0..n-1] into k palindromes
        return dp[k][n - 1];
    }
};