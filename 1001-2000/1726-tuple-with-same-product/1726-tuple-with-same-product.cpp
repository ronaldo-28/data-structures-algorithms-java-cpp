#pragma GCC optimize("Ofast,unroll-loops,inline")
#pragma GCC target("avx2,bmi,bmi2")

#include <vector>
#include <algorithm>

using namespace std;

static const int _ = []() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();

class Solution {
public:
    int tupleSameProduct(vector<int>& nums) {
        int n = nums.size();
        if (n < 4) return 0;

        // Всего пар n*(n-1)/2. Для 1000 это 499500.
        vector<int> products;
        products.reserve(n * (n - 1) / 2);

        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                products.push_back(nums[i] * nums[j]);
            }
        }

        // Сортировка вектора интов — это ультра-быстро (cache-friendly)
        sort(products.begin(), products.end());

        int totalTuples = 0;
        int k = 1;
        
        // Считаем группы одинаковых произведений
        for (int i = 1; i < products.size(); ++i) {
            if (products[i] == products[i - 1]) {
                k++;
            } else {
                if (k >= 2) {
                    totalTuples += 4 * k * (k - 1);
                }
                k = 1;
            }
        }
        
        // Не забываем обработать последнюю группу
        if (k >= 2) {
            totalTuples += 4 * k * (k - 1);
        }

        return totalTuples;
    }
};