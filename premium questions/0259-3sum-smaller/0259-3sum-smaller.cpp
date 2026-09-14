class Solution {
public: 

    //a + b + c < target
    //b +c  < target-a

    int twoSumSmaller(int start, int target, vector<int>& nums)
    {
        int i = start;
        int j = nums.size()-1;
        int count  =  0;

        //we want 2 nums < target, but if   smallest  >= target ne no count
        if(nums[i] > 0 &&  nums[i] >= target)
        {
            return 0;
        }

        while(i < j)
        {
            if(nums[i] + nums[j] < target)
            {
                auto n = j-i+1;

                //if you fix i, how many 2 numbers can u form?
                count += (j-i);
                ++i;
            }
            else
            {
                --j;
            }
        }

        return count;

    }


    int threeSumSmaller(vector<int>& nums, int target) {
        
        sort(nums.begin(), nums.end());

        int count = 0;

        for(int i = 0; i < (int)nums.size()-2; ++i)
        { 
            count += twoSumSmaller(i+1,  target - nums[i],  nums);
        }
        return count;
    }
};