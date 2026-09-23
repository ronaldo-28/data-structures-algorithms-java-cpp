class Solution {
public:
    int minOrAfterOperations(vector<int>& nums, int k) {
        int res = 0, mask = 0;

        for (int i = 29; i >= 0; i--) {
            int next = mask | 1 << i;
            int sum = 0, cur = next;

            for (auto& c : nums) {
                cur &= c;
                sum += !cur;
                if (!cur) cur = next;
            }

            if (nums.size() - sum <= k) mask = next;
            else res |= 1 << i;
        }

        return res;
    }
};