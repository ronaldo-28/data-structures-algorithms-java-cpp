class Solution {
    static int mod = 1_000_000_007;
    private int dfs(String n, int pos, boolean tight, int prev, int[][] dp){
        int len = n.length();
        if(pos==len) return 1;
        if(!tight && prev!=-1 && dp[pos][prev]!=-1) return dp[pos][prev];

        int dig = n.charAt(pos) - '0';
        int limit = tight?dig:9;
        long totalNums = 0;
        if(prev==-1){
            totalNums = (totalNums + dfs(n,pos+1,false,-1,dp))%mod;
            for(int num=1;num<=limit;num++){
                boolean newTight = tight && (num==dig);
                totalNums = (totalNums + dfs(n,pos+1,newTight,num,dp))%mod;
            }
        }
        else{
            if(prev>0 && (!tight || prev-1<=dig)){
                int num = prev - 1;
                boolean newTight = tight && (num==dig);
                totalNums = (totalNums + dfs(n,pos+1,newTight,num,dp))%mod;
            }
            if(prev<9 && (!tight || prev+1<=dig)){
                int num = prev + 1;
                boolean newTight = tight && (num==dig);
                totalNums = (totalNums + dfs(n,pos+1,newTight,num,dp))%mod;
            }
        }
        if(!tight && prev!=-1) return dp[pos][prev] = (int)totalNums;
        return (int)totalNums;

    }
    public int countSteppingNumbers(String low, String high) {
        int[][] dp = new int[high.length()][10];
        for(int i=0;i<dp.length;i++) Arrays.fill(dp[i],-1);
        int hi = dfs(high,0,true,-1,dp);
        for(int i=0;i<dp.length;i++) Arrays.fill(dp[i],-1);
        int lo = dfs(low,0,true,-1,dp);
        
        long ans = (hi - lo) %mod;
        if(ans<0) ans+=mod;
        int len = low.length();
        int j = 1;
        while(j<len){
            if(Math.abs(low.charAt(j)-low.charAt(j-1))!=1) return (int) ans;
            j++;
        }
        ans=(ans+1)%mod;
        return (int)ans;
    }
}