class Solution {
    //T(n,k) = T(n-1,k)*k: to each of the k buckets, we can add a new item
    //         +T(n-1,k-1): we can add a new bucket
    public int waysToDistribute(int n, int k) {
        long dp[] = new long[k+1], mod = (long)1e9+7;
        dp[1] = 1;
        for(int i = 2; i <= n; ++i){
            for(int j = Math.min(k,i); j >= 2; --j){
                dp[j] = (dp[j-1]+(j*dp[j])%mod)%mod;
            }
        }
        return (int)dp[k];
    }
}