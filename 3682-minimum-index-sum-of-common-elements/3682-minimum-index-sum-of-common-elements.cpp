// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto minimumSum(std::vector<int>& nums1, std::vector<int>& nums2)
        -> int {
        std::unordered_map<int, int> first_index;
        first_index.reserve(nums1.size());
        for (std::size_t i = 0; i < nums1.size(); ++i) {
            first_index.try_emplace(nums1[i], static_cast<int>(i));
        }

        int min_sum = std::numeric_limits<int>::max();
        for (std::size_t j = 0; j < nums2.size(); ++j) {
            auto found = first_index.find(nums2[j]);
            if (found != first_index.end()) {
                min_sum =
                    std::min(min_sum, found->second + static_cast<int>(j));
            }
        }

        return min_sum == std::numeric_limits<int>::max() ? -1 : min_sum;
    }
};