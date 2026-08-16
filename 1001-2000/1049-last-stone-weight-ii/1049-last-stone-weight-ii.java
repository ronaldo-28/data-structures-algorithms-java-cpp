class Solution {
    public int lastStoneWeightII(int[] stones) {
        int total=0;
        for(int num:stones){
            total+=num;
        }
        int[][] dp = new int[stones.length][(total/2)+1];

        for(int [] row:dp){
            Arrays.fill(row,-1);
        }
        
        return  total-2*help(stones,stones.length-1,total/2,dp);
    }
    public int help(int[] stones , int i , int target,int [][] dp){
            if(i<0||target==0){
                return 0;
            }
            if(dp[i][target]!=-1){
                return dp[i][target];
            }
            int notTake=help(stones,i-1,target,dp);
            int take =0;
            if(stones[i]<=target){
                take=stones[i]+help(stones,i-1,target-stones[i],dp);
            }
            dp[i][target]= Math.max(notTake,take);
            return dp[i][target];
    }
}