class Solution {
    static int[][] dp = new int[1001][1001];
    static int mod = (int)(1e9+7);
    public int rearrangeSticks(int n, int k) {
        if(k==0){
            return 0;
        }
        if(n==k){
            return 1;
        }
        if(dp[n][k]!=0){
            return dp[n][k];
        }
        long ans1 = rearrangeSticks(n-1,k-1);
        long ans2 = 1L * rearrangeSticks(n-1,k)*(n-1);
        ans2 %= mod;
        ans1+=ans2;
        ans1%=mod;
        return dp[n][k] = (int) (ans1);
    }
}