class Solution {
public:
    bool isTrionic(vector<int>& nums) {
        int i=0,p=0,q=0,n=nums.size();
        while(i<n-1&&nums[i+1]>nums[i])
            i++;
        p=i;
        while(i<n-1&&nums[i+1]<nums[i])
            i++;
        q=i;
        while(i<n-1&&nums[i+1]>nums[i])
            i++;
        if(i==n-1){
            if(p&&q&&p!=n-1&&q!=n-1)
                return true;
            else return false;
        }
        return false;
    }
};