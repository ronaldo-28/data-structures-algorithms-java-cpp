// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto minOperations(std::vector<int>& nums, int window_length)
        -> long long {
        MedianWindow window(nums);
        for (int i = 0; i < window_length; ++i) {
            window.add(nums[static_cast<std::size_t>(i)]);
        }
        std::int64_t best = window.cost(window_length);
        for (auto i = static_cast<std::size_t>(window_length); i < nums.size();
             ++i) {
            window.remove(nums[i - static_cast<std::size_t>(window_length)]);
            window.add(nums[i]);
            best = std::min(best, window.cost(window_length));
        }
        return best;
    }

   private:
    class MedianWindow {
       public:
        explicit MedianWindow(const std::vector<int>& nums)
            : sorted_vals_(compress(nums)),
              size_(static_cast<int>(sorted_vals_.size())),
              count_bit_(static_cast<std::size_t>(size_) + 1, 0),
              sum_bit_(static_cast<std::size_t>(size_) + 1, 0) {}

        void add(int value) {
            const int rank = rank_of(value);
            update(count_bit_, rank, 1);
            update(sum_bit_, rank, value);
        }

        void remove(int value) {
            const int rank = rank_of(value);
            update(count_bit_, rank, -1);
            update(sum_bit_, rank, -static_cast<std::int64_t>(value));
        }

        auto cost(int window_size) -> std::int64_t {
            const int median_pos = (window_size + 1) / 2;
            const int median_rank = find_kth(median_pos);
            const std::int64_t median_val =
                sorted_vals_[static_cast<std::size_t>(median_rank - 1)];

            const std::int64_t left_count = query(count_bit_, median_rank);
            const std::int64_t left_sum = query(sum_bit_, median_rank);
            const std::int64_t right_count = window_size - left_count;
            const std::int64_t right_sum = query(sum_bit_, size_) - left_sum;

            const std::int64_t left_cost = (median_val * left_count) - left_sum;
            const std::int64_t right_cost =
                right_sum - (median_val * right_count);
            return left_cost + right_cost;
        }

       private:
        static auto compress(const std::vector<int>& nums) -> std::vector<int> {
            std::vector<int> values(nums);
            std::ranges::sort(values);
            const auto extra = std::ranges::unique(values);
            values.erase(extra.begin(), extra.end());
            return values;
        }

        [[nodiscard]] auto rank_of(int value) const -> int {
            const auto pos = std::ranges::lower_bound(sorted_vals_, value);
            return static_cast<int>(pos - sorted_vals_.begin()) + 1;
        }

        void update(std::vector<std::int64_t>& tree, int idx,
                    std::int64_t delta) const {
            for (; idx <= size_; idx += idx & (-idx)) {
                tree[static_cast<std::size_t>(idx)] += delta;
            }
        }

        static auto query(const std::vector<std::int64_t>& tree, int idx)
            -> std::int64_t {
            std::int64_t total = 0;
            for (; idx > 0; idx -= idx & (-idx)) {
                total += tree[static_cast<std::size_t>(idx)];
            }
            return total;
        }

        [[nodiscard]] auto find_kth(int kth) const -> int {
            int pos = 0;
            for (int step = highest_power_leq(size_); step > 0; step >>= 1) {
                const int next = pos + step;
                if (next <= size_ &&
                    count_bit_[static_cast<std::size_t>(next)] < kth) {
                    kth -= static_cast<int>(
                        count_bit_[static_cast<std::size_t>(next)]);
                    pos = next;
                }
            }
            return pos + 1;
        }

        static auto highest_power_leq(int value) -> int {
            int power = 1;
            while (power <= value) {
                power <<= 1;
            }
            return power >> 1;
        }

        std::vector<int> sorted_vals_;
        int size_ = 0;
        std::vector<std::int64_t> count_bit_;
        std::vector<std::int64_t> sum_bit_;
    };
};