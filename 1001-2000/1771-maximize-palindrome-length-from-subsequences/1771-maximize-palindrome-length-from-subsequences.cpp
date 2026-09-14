#pragma GCC optimize("O3,unroll-loops")

static const int _ = []() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(nullptr);
    return 0;
}();

class Solution {
public:
    int longestPalindrome(std::string word1, std::string word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int n = len1 + len2;
        
        char s[2005];
        for(int i = 0; i < len1; ++i) s[i] = word1[i];
        for(int i = 0; i < len2; ++i) s[len1 + i] = word2[i];
        
        int dp[2005] = {0};
        int max_pal = 0;
        
        for (int i = n - 1; i >= 0; --i) {
            dp[i] = 1;
            int prev = 0;
            for (int j = i + 1; j < n; ++j) {
                int temp = dp[j];
                if (s[i] == s[j]) {
                    dp[j] = prev + 2;
                    if (i < len1 && j >= len1) {
                        if (dp[j] > max_pal) {
                            max_pal = dp[j];
                        }
                    }
                } else {
                    if (dp[j - 1] > dp[j]) {
                        dp[j] = dp[j - 1];
                    }
                }
                prev = temp;
            }
        }
        
        return max_pal;
    }
};