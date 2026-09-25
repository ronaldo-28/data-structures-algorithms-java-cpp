class Solution {
public:
    bool isPossibleToSplit(vector<int>& nums) {
        int freq[101]={0};
        for(int i=0;i<nums.size();i++)
        {
            freq[nums[i]]++;
        }
        for(int i=1;i<101;i++)
        {
            if(freq[i]>2)
            {
                return false;
            }
        }
        return true;
    }
};