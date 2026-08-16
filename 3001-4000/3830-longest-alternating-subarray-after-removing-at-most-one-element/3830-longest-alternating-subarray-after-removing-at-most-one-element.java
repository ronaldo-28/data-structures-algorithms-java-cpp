class Solution {
    public int longestAlternating(int[] nums) {
        int max=1;
        int count=1;
        int countrem=0;
        int last=0;
        int i=1;
        while(i<nums.length && nums[i]==nums[i-1])
            i++;
        if(i==nums.length)
            return 1;
        boolean big=nums[i-1]<nums[i]?true:false;
        int lastKey=nums[i-1];
        
        
        for(i=1;i<nums.length;i++)
            {
                if(nums[i]==lastKey)
                {
                    count=(i-last);
                    last=i;
                }
                else if((big && nums[i]<lastKey) || (!big && nums[i]>lastKey))
                {
                    count=(i-last);
                    last=i-1;
                    lastKey=nums[i];
                }
                else
                {
                    count++;
                    big=!big;
                    lastKey=nums[i];
                }
                // System.out.println(i+" "+last+" "+count);
                max=Math.max(max,count);
            }
        return max;
    }
}