#pragma GCC optimize("Ofast,unroll-loops,inline")
#pragma GCC target("avx2,bmi,bmi2")

#include <vector>
#include <string>

using namespace std;

static const int _ = []() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int countConsistentStrings(string allowed, vector<string>& words) {
        // Создаем маску. Всего 26 бит, влезет в int.
        unsigned int mask = 0;
        for (char c : allowed) {
            mask |= (1 << (c - 'a'));
        }

        int count = 0;
        // Проходим по вектору через ссылки, чтобы не копировать объекты
        for (const string& w : words) {
            const char* s = w.c_str();
            bool ok = true;
            
            // Ручной проход по C-строке до нулевого символа
            while (*s) {
                // Если бита буквы нет в маске — слово не подходит
                if (!(mask & (1 << (*s - 'a')))) {
                    ok = false;
                    break;
                }
                s++;
            }
            if (ok) count++;
        }

        return count;
    }
};