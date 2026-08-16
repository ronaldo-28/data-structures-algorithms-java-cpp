class Solution {
public:
    long long maxTotal(vector<int>& nums, string s) {

        int pre = -1;
        long long ans = 0;
        for(int i=0;i<nums.size();i++)
        {
            if(s[i]=='1')
            {
                if(pre == -1)
                    ans+=nums[i];
                else
                {
                    if(nums[pre] > nums[i])
                    {
                        ans+=nums[pre];
                        pre = i;
                    }
                    else
                    {
                        ans+=nums[i];
                    }
                }
            }
            else
            {
                pre = i;
            }
        }
        return ans;
    }
};