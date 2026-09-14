#include <vector>
#include <algorithm>

using namespace std;

#pragma GCC optimize("Ofast,unroll-loops")

class Solution {
    // Используем статику, чтобы не тратить время на аллокации
    int cnt[100005];
    int pref[100005];
    const int MOD = 1e9 + 7;

public:
    int sumOfFlooredPairs(vector<int>& nums) {
        int max_v = 0;
        // 1. Очистка и заполнение частотного массива
        for (int i = 0; i <= 100000; ++i) cnt[i] = 0;
        
        for (int x : nums) {
            cnt[x]++;
            if (x > max_v) max_v = x;
        }

        // 2. Построение префиксных сумм
        for (int i = 1; i <= max_v; ++i) {
            pref[i] = pref[i - 1] + cnt[i];
        }

        long long total_sum = 0;

        // 3. Основной расчет через гармонический ряд
        for (int x = 1; x <= max_v; ++x) {
            if (cnt[x] == 0) continue;

            long long current_x_contribution = 0;
            // Перебираем все кратные числа x: x, 2x, 3x...
            for (int upper = x, quotient = 1; upper <= max_v; upper += x, ++quotient) {
                int next_upper = min(max_v, upper + x - 1);
                // Количество чисел в диапазоне [upper, next_upper]
                int count_in_range = pref[next_upper] - pref[upper - 1];
                
                // Добавляем к сумме: (количество чисел) * (результат floor)
                current_x_contribution += (long long)count_in_range * quotient;
            }
            
            // Умножаем вклад на количество вхождений самого делителя x
            total_sum = (total_sum + (current_x_contribution % MOD) * cnt[x]) % MOD;
        }

        return (int)total_sum;
    }
};

static const int fast_io = []() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();