class Solution {
public:
    int minimumDifference(vector<int>& nums, int k) {
        int left = 0, bottom = 0, right_or = 0, res = INT_MAX;
        for (int right = 0; right < nums.size(); right++) {
            right_or |= nums[right];
            while (left <= right && (right_or | nums[left]) > k) {
                if ((right_or | nums[left]) - k < res)
                    res = (right_or | nums[left]) - k;
                left++;
                if (left > bottom) {
                    for (int i = right - 1; i > bottom; i--)
                        nums[i] |= nums[i + 1];
                    bottom = right;
                    right_or = 0;
                }
            }
            if (left <= right && k - (nums[left] | right_or) < res)
                res = k - (nums[left] | right_or);
        }
        return res;
    }
};