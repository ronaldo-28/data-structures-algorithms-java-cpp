class Solution {
    int mod = 1000000007;

    public long rec(int i,long[] dp ){
        if(dp[i]!=-1){
            return dp[i];
        }
        if(i<26){
            dp[i] =  1l;
        }
        else if(i==26){
            dp[i] =  2;
        }else{
            dp[i] =  ((rec(i-26,dp)%mod) + (rec(i-27,dp)%mod))%mod;
        }
        return dp[i]%mod;
    }

    public int ans(String s, int t,long[] dp){
        long a = 0l;
        for(int i = 0;i<s.length();i++){
            int x = s.charAt(i)-'a';
            a = (a + dp[t+x])%mod;
            // a=(a+rec(t+x,dp))%mod;
        }
        return (int)(a%mod);
    }
    public int lengthAfterTransformations(String s, int t) {

        long[] dp = new long[t+26];
        for(int i = 0;i<26;i++){
            dp[i] = 1l;
        }
        dp[26] = 2l;
        for(int i = 27;i<t+26;i++){
            dp[i] = (dp[i-26] + dp[i-25])%mod;
        }
        // Arrays.fill(dp,-1l);

        return ans(s,t,dp);
    }
}