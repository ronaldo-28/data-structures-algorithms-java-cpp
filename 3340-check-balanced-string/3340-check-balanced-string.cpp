class Solution {
public:
    bool isBalanced(string nums) {
        int sum1,sum2=0;
        for(int i=0;i<nums.size();i=i+2)
        {
            sum1+=(nums[i]-'0');
        }
        for(int i=1;i<nums.size();i=i+2)
        {
            sum2+=(nums[i]-'0');
        }
        return sum1==sum2;
    }
};