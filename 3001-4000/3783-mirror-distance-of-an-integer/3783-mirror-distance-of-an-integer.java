class Solution 
{
    public int mirrorDistance(int n) 
    {
        int rev=reverse(n);
        return Math.abs(rev-n);
    }
    public int reverse(int n)
    {
        int ans=0;
        while(n>0)
        {
            int dig=n%10;
            ans=ans*10+dig;
            n=n/10;
        }
        return ans;
    }
}
