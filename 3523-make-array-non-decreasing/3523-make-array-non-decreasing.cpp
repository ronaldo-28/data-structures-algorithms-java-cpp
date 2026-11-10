class Solution {
public:
    int maximumPossibleSize(vector<int>& nums) {
        int ans=1;
        int n=nums.size();
        int temp=nums[0];
        for(int i=1;i<n;i++){
            if(temp<=nums[i]){
                ans++;
                temp=nums[i];
            }
        }
        return ans;
    }
};