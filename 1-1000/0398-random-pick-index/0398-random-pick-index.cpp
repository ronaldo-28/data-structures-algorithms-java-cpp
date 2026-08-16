class Solution {
public:
    vector<int> nums;
    Solution(vector<int>& v1) {
        nums=v1;
    }
    
    int pick(int target) {
        int index=-1;
        while(1){
            index=rand();
            index%=nums.size();
            if(nums[index]==target){
                return index;
            }
        }
        return -1;
    }
};

/**
 * Your Solution object will be instantiated and called as such:
 * Solution* obj = new Solution(nums);
 * int param_1 = obj->pick(target);
 */