/**
 * // This is the ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * class ArrayReader {
 *   public:
 *     int get(int index);
 * };
 */

namespace Utility {
    auto const kInvalidIdx{static_cast<size_t>(std::pow(2, 31) - 1)};
    auto constexpr kNumsCountMax{static_cast<size_t>(1e4)};
}
class Solution {
public:
    int search(
        ArrayReader const & reader_,
        int const target_
    ) const;
};

int Solution::search(
    ArrayReader const & reader_,
    int const target_
) const {
    using namespace Utility;
    for(size_t left{0}, mid{(kNumsCountMax - 1) / 2}, right{kNumsCountMax - 1};
        left + 1 <= right + 1; mid = left + (right - left) / 2
    ) {
        auto const num{reader_.get(mid)};
        if(target_ == num) {
            return mid;
        } else if(target_ < num) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}