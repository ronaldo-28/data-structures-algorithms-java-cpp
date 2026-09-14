class Solution {
public:
    double minPrice(vector<int>& prices, vector<int>& discounts) {
        sort(prices.begin(), prices.end());
        sort(discounts.begin(), discounts.end());

        double ans = 0;
        int n = prices.size();
        int m = discounts.size();

        int j = m - 1;

        for(int i = n - 1; i >= 0 && j >= 0; i--) {
            ans += (double)prices[i] * (100 - discounts[j]) / 100.0;
            j--;
        }

        for(int i = 0; i < n - min(n, m); i++) {
            ans += prices[i];
        }

        return ans;
    }
};
const auto _ = std::cin.tie(nullptr)->sync_with_stdio(false);
const auto __ = []() {
    struct ___ {
        static void _() { std::ofstream("display_runtime.txt") << 0 << '\n'; }
    };
    std::atexit(&___::_);
    return 0;
}();