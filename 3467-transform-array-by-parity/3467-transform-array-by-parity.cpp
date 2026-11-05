class Solution {
public:
    vector<int> transformArray(vector<int>& nums) {
        for(int& num:nums){
            num=num%2;
        }
        sort(nums.begin(),nums.end());
        return nums;
    }
};