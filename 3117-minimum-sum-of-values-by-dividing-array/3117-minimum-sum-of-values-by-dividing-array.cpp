// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto minimumValueSum(std::vector<int>& nums,
                                std::vector<int>& and_values) -> int {
        const int nums_size = static_cast<int>(nums.size());
        const int parts = static_cast<int>(and_values.size());

        std::vector<int> prev(nums_size + 1, INF);
        std::vector<int> curr(nums_size + 1, INF);
        prev[0] = 0;

        Stack read;
        Stack write;

        for (int col = 1; col <= parts; ++col) {
            const Element base{.target = and_values[col - 1]};
            std::ranges::fill(curr, INF);
            read.clear();

            const int max_idx = nums_size - (parts - col);
            for (int idx = col; idx <= max_idx; ++idx) {
                const Element element{.and_value = nums[idx - 1],
                                      .cost = prev[idx - 1],
                                      .target = base.target};
                const int min_cost = build_next_stack(read, element, write);

                curr[idx] = min_cost < INF ? min_cost + element.and_value : INF;
                std::swap(read, write);
            }
            std::swap(prev, curr);
        }

        const int answer = prev[nums_size];
        return answer >= INF ? -1 : answer;
    }

   private:
    static constexpr int INF = 0x3f3f3f3f;

    struct Element {
        int and_value = 0;
        int cost = 0;
        int target = 0;
    };

    using Stack = std::vector<std::pair<int, int>>;

    static auto build_next_stack(const Stack& read, const Element& element,
                                 Stack& write) -> int {
        int min_cost = INF;
        write.clear();
        write.emplace_back(element.and_value, element.cost);
        if (element.and_value == element.target) {
            min_cost = std::min(min_cost, element.cost);
        }

        for (const auto& [prev_and, prev_cost] : read) {
            const int folded = prev_and & element.and_value;
            if (folded == write.back().first) {
                write.back().second = std::min(write.back().second, prev_cost);
            } else {
                write.emplace_back(folded, prev_cost);
            }
            if (write.back().first == element.target) {
                min_cost = std::min(min_cost, write.back().second);
            }
        }
        return min_cost;
    }
};