class Solution {
public:
    vector<string> getWordsInLongestSubsequence(vector<string>& words, vector<int>& groups) {
        int n = words.size();
        vector<int> dp(n, 1);
        vector<int> parent(n, -1);
        
        int best_len = 0;
        int best_idx = -1;

        // Bottom-up Dynamic Programming
        for (int i = 0; i < n; ++i) {
            int sz = words[i].size();

            for (int j = 0; j < i; ++j) {
                // Check if valid transition: different group & same word length
                if (groups[i] != groups[j] && words[j].size() == sz) {
                    
                    // Hamming distance check (must differ by exactly 1 character)
                    int diff = 0;
                    for (int k = 0; k < sz && diff <= 1; ++k) {
                        if (words[i][k] != words[j][k]) ++diff;
                    }

                    if (diff == 1 && dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        parent[i] = j;
                    }
                }
            }

            // Track overall maximum length and ending index
            if (dp[i] > best_len) {
                best_len = dp[i];
                best_idx = i;
            }
        }

        // Reconstruct the solution path using the parent array
        vector<string> result;
        for (int curr = best_idx; curr != -1; curr = parent[curr]) {
            result.push_back(words[curr]);
        }

        // Reverse to restore original relative order
        reverse(result.begin(), result.end());
        return result;
    }
};