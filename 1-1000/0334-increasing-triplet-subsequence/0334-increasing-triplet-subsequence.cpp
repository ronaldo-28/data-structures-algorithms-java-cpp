class Solution {
public:
    bool increasingTriplet(vector<int>& nums) {
        int i = INT_MAX, j = INT_MAX;
        for(int k = 0; k < nums.size(); k++) {
            if(nums[k] <= i) i = nums[k];
            else if(nums[k] <= j) j = nums[k];
            else return true;
        }
        return false;
    }
};