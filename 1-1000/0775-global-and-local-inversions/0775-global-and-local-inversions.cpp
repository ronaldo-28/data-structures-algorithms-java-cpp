class Solution {
public:
    bool isValid(int i,vector<int>&nums){
        int n=nums.size();
        if(i==n-1 && (nums[i]==n-1 || nums[i]==n-2)) return true;
        if((nums[i]==i && nums[i+1]==i+1) || 
            (nums[i]==i+1 && nums[i+1]==i)) return true;
        return false;
    }

    bool isIdealPermutation(vector<int>& nums) {
        int n=nums.size();
        
        for(int i=0;i<n;){
            if(nums[i]==i){
                i++;
                continue;
            }
            if(!isValid(i,nums)) return false;
            i+=2;
        }
        return true;
    }
};