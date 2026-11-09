class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {

        const auto& bit_length = [](int64_t x) {
            return (x ? std::__lg(x) : -1) + 1;
        };

        vector<int64_t> a(1 << bit_length(ranges::max(nums)));

        for (const auto& x: nums) {
            ++a[x];
        }

        FST(a, false);
        for (int i = 0; i < a.size(); i++) {
           a[i] = a[i] * a[i] * a[i];
        }

        FST(a, true);
        int result = 0;
        for (const auto& x: a) {
            if (x) {
                ++result;
            }
        }
        return result;
    }

private:
    void FST(auto& a, bool inv) {

        for (int n = a.size(), step = 1; step < n; step <<= 1) {
            for (int i = 0; i < n; i += step << 1) {
                for (int j = i; j < i + step; ++j) {
                    auto &u = a[j], &v = a[j + step];
                    tie(u, v) = pair(u + v, u - v);
                }
            }
        }
        if (inv) {
            for (auto& x : a) {
                x /= a.size();
            }
        }
    }
};