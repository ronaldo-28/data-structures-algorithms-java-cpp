class Solution {
public:
    int maxEqualFreq(const std::vector<int>& nums) const {
        constexpr int NUM_MAX = 100'000;
        constexpr int COUNT_MAX = 100'000;

        const int size = nums.size();
        std::array<int, NUM_MAX + 1> aryNumToCount = {};
        std::array<int, COUNT_MAX + 1> aryCountToCount = {};
        int countNumsDist = 0;
        int countMax = 0;
        int sizePrefixMax = 0;

        for (int idx = 0; idx < size; ++idx) {
            const int num = nums[idx];
            const int countOld = aryNumToCount[num];
            const int countNew = ++aryNumToCount[num];
            --aryCountToCount[countOld];
            ++aryCountToCount[countNew];
            countMax = std::max(countMax, countNew);
            if (countOld == 0) {
                ++countNumsDist;
            }

            if (countMax == 1 ||
                (aryCountToCount[1] == 1 && (countNumsDist - 1) == aryCountToCount[countMax]) ||
                (aryCountToCount[countMax] == 1 && (countNumsDist - 1) == aryCountToCount[countMax - 1])) {
                sizePrefixMax = std::max(sizePrefixMax, idx + 1);
            }
        }

        return sizePrefixMax;
    }
};