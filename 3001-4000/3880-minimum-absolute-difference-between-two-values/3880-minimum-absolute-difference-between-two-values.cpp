class Solution {
public:
    int minAbsoluteDifference(vector<int>& nums) {
        int idx1 = -1, idx2 = -1;
        int res = INT_MAX;
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] == 1){
                idx1 = i;
                if (idx2 != -1)
                    res = std::min(abs(idx1-idx2), res);
            }
            else if (nums[i] == 2) {
                idx2 = i;
                if (idx1 != -1)
                    res = std::min(abs(idx1-idx2), res);
            }
        }
        return (res == INT_MAX) ? -1: res;
    }
};