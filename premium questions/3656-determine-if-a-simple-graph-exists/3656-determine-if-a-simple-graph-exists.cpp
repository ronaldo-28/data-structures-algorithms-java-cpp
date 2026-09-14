// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto simpleGraphExists(std::vector<int>& degrees) -> bool {
        const int count = static_cast<int>(degrees.size());
        std::vector<int> degree_count(static_cast<std::size_t>(count), 0);

        std::int64_t total_sum = 0;
        for (const int deg : degrees) {
            if (deg >= count) {
                return false;
            }
            degree_count[static_cast<std::size_t>(deg)]++;
            total_sum += deg;
        }

        if ((total_sum % 2) != 0) {
            return false;
        }

        for (int deg = count - 1; deg > 0;) {
            if (degree_count[static_cast<std::size_t>(deg)] == 0) {
                deg--;
                continue;
            }
            if (degree_count[static_cast<std::size_t>(deg)] > deg) {
                degree_count[static_cast<std::size_t>(deg)] -= deg + 1;
                degree_count[static_cast<std::size_t>(deg - 1)] += deg;
                continue;
            }
            if (!distribute_edges(degree_count, deg)) {
                return false;
            }
        }

        for (int deg = 1; deg < count; deg++) {
            if (degree_count[static_cast<std::size_t>(deg)] > 0) {
                return false;
            }
        }
        return true;
    }

   private:
    static auto distribute_edges(std::vector<int>& degree_count, int deg)
        -> bool {
        int leftover = degree_count[static_cast<std::size_t>(deg)] - 1;
        degree_count[static_cast<std::size_t>(deg)] = 0;
        int remaining = deg - leftover;

        for (int lower = deg - 1; lower > 0; lower--) {
            const auto slot = static_cast<std::size_t>(lower);
            if (degree_count[slot] > 0) {
                const int take = degree_count[slot] < remaining
                                     ? degree_count[slot]
                                     : remaining;
                remaining -= take;
                degree_count[slot] += leftover - take;
                leftover = take;
            } else if (leftover != 0) {
                degree_count[slot] += leftover;
                leftover = 0;
            }
            if (leftover == 0 && remaining == 0) {
                break;
            }
        }

        return remaining == 0;
    }
};