// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto powerUpdate(std::vector<int>& nums, int initial_power,
                            std::vector<std::vector<int>>& queries)
        -> std::vector<int> {
        std::vector<int> values;
        values.reserve(nums.size() + queries.size());
        for (const int value : nums) {
            values.push_back(value);
        }
        for (const auto& query : queries) {
            values.push_back(query[0]);
        }
        std::ranges::sort(values);
        values.erase(std::ranges::unique(values).begin(), values.end());

        const int compressed_size = static_cast<int>(values.size());
        std::vector<int> fenwick(static_cast<std::size_t>(compressed_size) + 1,
                                 0);

        int total = 0;
        for (const int value : nums) {
            fenwick_add(fenwick, rank_of(values, value));
            ++total;
        }

        std::vector<int> result;
        result.reserve(queries.size());
        std::int64_t power = initial_power;
        for (const auto& query : queries) {
            const int value = query[0];
            const int k_val = query[1];
            fenwick_add(fenwick, rank_of(values, value));
            ++total;
            const int kth_rank = fenwick_kth(fenwick, total - k_val + 1);
            const int exponent = values[static_cast<std::size_t>(kth_rank) - 1];
            std::int64_t accumulated = 1;
            std::int64_t current = power % MOD;
            for (int exp = exponent; exp > 0; exp >>= 1) {
                if ((exp & 1) != 0) {
                    accumulated = accumulated * current % MOD;
                }
                current = current * current % MOD;
            }
            power = accumulated;
            result.push_back(static_cast<int>(power));
        }
        return result;
    }

   private:
    static constexpr int LOG_MAX = 20;
    static constexpr std::int64_t MOD = 1000000007;

    static auto rank_of(const std::vector<int>& values, int value) -> int {
        const auto position =
            std::ranges::lower_bound(values, value) - values.begin();
        return static_cast<int>(position) + 1;
    }

    static void fenwick_add(std::vector<int>& fenwick, int idx) {
        const int size = static_cast<int>(fenwick.size()) - 1;
        for (int i = idx; i <= size; i += i & -i) {
            ++fenwick[static_cast<std::size_t>(i)];
        }
    }

    static auto fenwick_kth(const std::vector<int>& fenwick, int k_rank)
        -> int {
        const int size = static_cast<int>(fenwick.size()) - 1;
        int pos = 0;
        int remaining = k_rank;
        for (int bit = LOG_MAX; bit >= 0; --bit) {
            const int next_pos = pos + (1 << bit);
            if (next_pos <= size &&
                fenwick[static_cast<std::size_t>(next_pos)] < remaining) {
                pos = next_pos;
                remaining -= fenwick[static_cast<std::size_t>(next_pos)];
            }
        }
        return pos + 1;
    }
};