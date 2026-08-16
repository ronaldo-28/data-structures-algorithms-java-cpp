// Time:  O(n)
// Space: O(1)

class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        // O(n): Check that elements are consecutive;
        //   more than one gap means it's not possible
        int gaps = abs(nums[0] - nums[n - 1]) > 1;
        for (int i = n - 1; gaps < 2 && i--; )
            gaps += abs(nums[i] - nums[i + 1]) > 1;
        if (gaps > 1)
            return -1;
        // O(n): Find the 0, check the direction, & solve
        int z = find(nums.begin(), nums.end(), 0) - nums.begin();
        return nums[(z + 1) % n] > 1
                    ? min(z + 2, n - z)
                    : min(z, n - z + 2);
    }
};