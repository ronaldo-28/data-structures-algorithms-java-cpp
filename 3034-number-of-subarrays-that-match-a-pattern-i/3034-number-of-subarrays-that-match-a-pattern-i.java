class Solution {
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int count=0;
        int n=nums.length;
        int m=pattern.length;
        for(int i=0;i<n-m;i++)
        {
            boolean Matches=true;
            for(int j=0;j<m;j++)
            {
                if(pattern[j]==1 && nums[i+j+1]<=nums[i+j])
                {
                    Matches=false;
                    break;
                }
                else  if(pattern[j]==0 && nums[i+j+1]!=nums[i+j])
                {
                    Matches=false;
                    break;
                }
                 if(pattern[j]==-1 && nums[i+j+1]>=nums[i+j])
                {
                    Matches=false;
                    break;
                }
            }
            if(Matches)
            {
                count++;
            }
        }
        return count;
    }
}