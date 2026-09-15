class Solution {
    public int minimumArrayLength(int[] nums) {
        int m=Integer.MAX_VALUE;
        for(int s:nums)
        m=Math.min(m,s);
        int c=0;
        for(int x:nums)
        {
            if(x%m!=0)
            return 1;
            if(x==m)
            c++;
        }
        return (c+1)/2;
    }
}