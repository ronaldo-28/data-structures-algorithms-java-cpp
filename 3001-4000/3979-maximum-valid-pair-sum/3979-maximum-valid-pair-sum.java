class Solution {
    public int maxValidPairSum(int[] a, int k) {
        int n=a.length;
        int[] sf=new int[n+1];
        for(int i=n-1;i>=0;i--)
            sf[i]=Math.max(a[i],sf[i+1]); // maximum value from i to end
        int ans=0;
        for(int i=0;i+k<n;i++)
            ans=Math.max(ans,a[i]+sf[i+k]); // best partner for index i
        return ans;
    }
}