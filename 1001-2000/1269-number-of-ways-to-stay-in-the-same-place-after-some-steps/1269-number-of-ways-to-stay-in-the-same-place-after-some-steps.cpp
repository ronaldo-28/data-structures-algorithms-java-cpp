#include <algorithm>
#include <cstring>

class Solution {
    long long dp[2][502]; // Статический массив для слоев DP
    const int MOD = 1e9 + 7;

public:
    int numWays(int steps, int arrLen) {
        // Эффективная длина: за 'steps' шагов не уйти дальше steps/2 и вернуться
        int max_pos = std::min(steps, arrLen - 1);
        max_pos = std::min(max_pos, steps / 2 + 1);

        std::memset(dp, 0, sizeof(dp));
        dp[0][0] = 1;

        for (int i = 1; i <= steps; ++i) {
            int curr = i % 2;
            int prev = (i - 1) % 2;
            
            for (int j = 0; j <= max_pos; ++j) {
                // Вариант 1: Остаться на месте
                long long ways = dp[prev][j];
                
                // Вариант 2: Прийти слева (шаг вправо)
                if (j > 0) ways += dp[prev][j - 1];
                
                // Вариант 3: Прийти справа (шаг влево)
                if (j < max_pos) ways += dp[prev][j + 1];
                
                dp[curr][j] = ways % MOD;
            }
        }

        return (int)dp[steps % 2][0];
    }
};