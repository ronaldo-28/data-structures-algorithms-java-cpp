#pragma GCC optimize("Ofast,inline,unroll-loops")
#include <vector>
#include <string>
#include <cstring>

using namespace std;

static const int MOD = 1e9 + 7;
static int freq[1000][26];
static long long dp[1001];

static const int speedup = []() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int numWays(vector<string>& words, string target) {
        int n = words.size();
        int word_len = words[0].length();
        int target_len = target.length();

        // 1. Быстрая очистка и подсчет частот через сырые массивы
        memset(freq, 0, sizeof(freq));
        for (const string& w : words) {
            for (int j = 0; j < word_len; ++j) {
                freq[j][w[j] - 'a']++;
            }
        }

        // 2. DP с жесткими границами
        memset(dp, 0, sizeof(dp));
        dp[0] = 1;

        for (int j = 0; j < word_len; ++j) {
            // Оптимизация: i не может быть больше количества просмотренных колонок (j+1)
            // и не может быть меньше, чем необходимо для завершения target (target_len - (word_len - 1 - j))
            int min_i = max(1, target_len - (word_len - j));
            int max_i = min(target_len, j + 1);
            
            for (int i = max_i; i >= min_i; --i) {
                int c = target[i-1] - 'a';
                if (freq[j][c]) {
                    dp[i] = (dp[i] + dp[i-1] * freq[j][c]) % MOD;
                }
            }
        }

        return (int)dp[target_len];
    }
};