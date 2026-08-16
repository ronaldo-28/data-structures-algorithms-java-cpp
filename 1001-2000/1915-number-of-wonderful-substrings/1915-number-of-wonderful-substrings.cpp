#include <string>
#include <vector>

using namespace std;

static const int _ = []() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    return 0;
}();

class Solution {
public:
    long long wonderfulSubstrings(string word) {
        // Статический массив частот масок (2^10 = 1024 состояний)
        long long counts[1024] = {0};
        counts[0] = 1; 
        
        int current_mask = 0;
        long long result = 0;
        
        const char* p = word.c_str();
        while (*p) {
            // Обновляем маску для текущего символа
            current_mask ^= (1 << (*p - 'a'));
            
            // 1. Все буквы четные (маска совпадает с одной из предыдущих)
            result += counts[current_mask];
            
            // 2. Одна буква нечетная (перебор 10 бит)
            for (int i = 0; i < 10; ++i) {
                result += counts[current_mask ^ (1 << i)];
            }
            
            counts[current_mask]++;
            p++;
        }
        
        return result;
    }
};