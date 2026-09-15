// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming,readability-identifier-length)
    static auto minimumCost(std::vector<int>& nums, int k, int dist)
        -> std::int64_t {
        Solver solver(nums, dist);
        return solver.solve(k);
    }

   private:
    static constexpr std::int64_t MAX_N = 100001;

    class Solver {
       public:
        Solver(const std::vector<int>& nums, int dist)
            : nums_(nums),
              dist_(dist),
              deleted_(nums.size(), false),
              in_lo_(nums.size(), false) {}

        auto solve(int subarrays) -> std::int64_t {
            const int need = subarrays - 1;
            const int size = static_cast<int>(nums_.size());

            const int window_end = std::min(dist_ + 2, size);

            for (int idx = 1; idx < window_end; ++idx) {
                const std::int64_t key = encode(idx);
                lo_.push(key);
                in_lo_[static_cast<std::size_t>(idx)] = true;
                lo_sum_ += nums_[static_cast<std::size_t>(idx)];
                ++lo_active_;
            }
            rebalance(need);

            std::int64_t answer = nums_[0] + lo_sum_;

            for (int idx = window_end; idx < size; ++idx) {
                const int out_idx = idx - dist_ - 1;
                deleted_[static_cast<std::size_t>(out_idx)] = true;
                if (in_lo_[static_cast<std::size_t>(out_idx)]) {
                    lo_sum_ -= nums_[static_cast<std::size_t>(out_idx)];
                    --lo_active_;
                }

                const std::int64_t key = encode(idx);
                lo_purge();
                hi_purge();
                if (!lo_.empty() && key < lo_.top()) {
                    lo_.push(key);
                    in_lo_[static_cast<std::size_t>(idx)] = true;
                    lo_sum_ += nums_[static_cast<std::size_t>(idx)];
                    ++lo_active_;
                } else {
                    hi_.push(key);
                    in_lo_[static_cast<std::size_t>(idx)] = false;
                }

                rebalance(need);

                const std::int64_t cost = nums_[0] + lo_sum_;
                answer = std::min(answer, cost);
            }

            return answer;
        }

       private:
        [[nodiscard]] auto encode(int idx) const -> std::int64_t {
            return (static_cast<std::int64_t>(
                        nums_[static_cast<std::size_t>(idx)]) *
                    MAX_N) +
                   idx;
        }

        [[nodiscard]] static auto key_index(std::int64_t key) -> int {
            return static_cast<int>(key % MAX_N);
        }

        [[nodiscard]] static auto key_value(std::int64_t key) -> int {
            return static_cast<int>(key / MAX_N);
        }

        void lo_purge() {
            while (!lo_.empty() &&
                   deleted_[static_cast<std::size_t>(key_index(lo_.top()))]) {
                lo_.pop();
            }
        }

        void hi_purge() {
            while (!hi_.empty() &&
                   deleted_[static_cast<std::size_t>(key_index(hi_.top()))]) {
                hi_.pop();
            }
        }

        void rebalance(int need) {
            lo_purge();
            hi_purge();
            while (lo_active_ > need && !lo_.empty()) {
                lo_purge();
                if (lo_.empty()) {
                    break;
                }
                const std::int64_t moved = lo_.top();
                lo_.pop();
                in_lo_[static_cast<std::size_t>(key_index(moved))] = false;
                lo_sum_ -= key_value(moved);
                --lo_active_;
                hi_.push(moved);
            }
            while (lo_active_ < need && !hi_.empty()) {
                hi_purge();
                if (hi_.empty()) {
                    break;
                }
                const std::int64_t moved = hi_.top();
                hi_.pop();
                in_lo_[static_cast<std::size_t>(key_index(moved))] = true;
                lo_sum_ += key_value(moved);
                ++lo_active_;
                lo_.push(moved);
            }
            lo_purge();
            hi_purge();
        }

        std::vector<int> nums_;
        int dist_;
        std::priority_queue<std::int64_t> lo_;
        std::priority_queue<std::int64_t, std::vector<std::int64_t>,
                            std::greater<>>
            hi_;
        int lo_active_ = 0;
        std::int64_t lo_sum_ = 0;
        std::vector<bool> deleted_;
        std::vector<bool> in_lo_;
    };
};