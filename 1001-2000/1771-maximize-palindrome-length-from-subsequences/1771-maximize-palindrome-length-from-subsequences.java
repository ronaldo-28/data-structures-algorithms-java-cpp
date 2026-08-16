class Solution {
public:
    int longestPalindrome(string word1, string word2) {
        
        string s = word1 + word2;

        int n = s.size(), m = word1.size();

        vector<vector<int>> dp(n, vector<int>(n));

        for(int i = n - 1; i >= 0; --i) {

            for(int j = i; j < n; ++j) {

                if(i == j) {

                    dp[i][j] = 1;
                }
                else {

                    if(j - i + 1 == 2) {

                        dp[i][j] = s[i] == s[j] ? 2 : 1;
                    }
                    else {

                        if(s[i] == s[j]) {

                            dp[i][j] = 2 + dp[i + 1][j - 1];
                        }
                        else {

                            dp[i][j] = max(dp[i + 1][j], dp[i][j - 1]);
                        }
                    }
                }
            }
        }

        int ans = 0;

        for(int i = 0; i < m; ++i) {

            for(int j = m; j < n; ++j) {

                if(s[i] == s[j]) {

                    ans = max(ans, 2 + dp[i + 1][j - 1]);
                }
            }
        }
        
        return ans;
    }
};