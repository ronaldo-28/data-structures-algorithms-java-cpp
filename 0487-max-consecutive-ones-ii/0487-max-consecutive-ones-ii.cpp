class Solution {
public:
    int findMaxConsecutiveOnes(std::vector<int>& nums) {
        const int n = (int)nums.size();
        int l = 0, zeros = 0, best = 0;
        for (int r = 0; r < n; ++r) {
            if (nums[r] == 0) ++zeros;                              // 1. 納入
            while (zeros > 1) {                                     // 🔴 2. 修復到合法
                if (nums[l] == 0) --zeros;
                ++l;
            }
            if (r - l + 1 > best) best = r - l + 1;                 // 🔴 3. 修復後才更新
        }
        return best;
    }
};