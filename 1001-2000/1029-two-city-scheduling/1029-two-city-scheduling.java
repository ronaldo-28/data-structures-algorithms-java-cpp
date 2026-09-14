class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int n= (costs.length/2);
        int dp[][]= new int[n+1][n+1];
        for(int i=0; i<dp.length; i++){
            Arrays.fill(dp[i],-1);
        }
        return rec(costs,0,0,dp);
    }
    public static int rec(int costs[][], int a, int b, int dp[][]){
        if(a+b==costs.length){
            return 0;
        }
        if(dp[a][b]!=-1){
            return dp[a][b];
        }
        int val1= (int)(1e8);
        int val2=(int)(1e8);
        if(a<(costs.length/2)){
            val1= costs[a+b][0] + rec(costs, a+1,b,dp);
        }
        if(b<(costs.length/2)){
            val2= costs[a+b][1] + rec(costs, a, b+1,dp);
        }
        return dp[a][b]= Math.min(val1, val2);
    }
}