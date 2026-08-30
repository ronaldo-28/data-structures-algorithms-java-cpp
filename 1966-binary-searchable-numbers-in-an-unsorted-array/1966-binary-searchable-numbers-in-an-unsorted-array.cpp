class Solution {
public:
    int binarySearchableNumbers(vector<int>& nums) {
        int ans = 0;
        int mx = -1e5 - 1, mn = 1e5 + 1;;
        vector<bool> possible(nums.size(), true);
        for (int i = 0; i < nums.size(); i++) {
            if (mx > nums[i]) {
                possible[i] = false;
            } else {
                mx = nums[i];
            }
            if (mn < nums[nums.size() - i - 1]) {
                possible[nums.size() - i - 1] = false;
            } else {
                mn = nums[nums.size() - i - 1];
            }
        }
        for (int i = 0; i < nums.size(); i++) {
            if (possible[i]) ans++;
        }
        return ans;
    }
};