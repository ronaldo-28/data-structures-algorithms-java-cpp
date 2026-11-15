class Solution
{
    public boolean canMakeEqual(int[] nums, int k)
    {
        return check(nums,1,k) || check(nums,-1,k);
    }

    private boolean check(int [] nums, int v, int k)
    {
        int l = nums.length;
        
        if(l==1)
            return nums[0]==v;

        int q = 0;
        
        for(int p=nums[l-1], i=l-2; i>=0 && k>=0; p=q, i--)
        {
            q = nums[i];

            if(p!=v)
            {
                q *= -1;
                k--;
            }
        }

        return q==v && k>=0;
    }
}