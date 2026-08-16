class Solution {
    public int firstUniqueFreq(int[] nums) {
        int max =0;
        for(int i=0;i<nums.length;i++)
            {
                if(nums[i]>max)
                {
                    max = nums[i];
                }
            }
        int [] freq = new int[max+1];
        for(int i=0;i<nums.length;i++)
        {
            freq[nums[i]]++;
        }
        int [] freqcount = new int[nums.length+1];
        for(int i=0;i<freq.length;i++)
        {
            if(freq[i]>0)
                freqcount[freq[i]]++;
        }
        for(int n : nums)
            {
                if(freqcount[freq[n]] == 1)
                {
                    return n;
                }
            }
        return -1;
    }
}