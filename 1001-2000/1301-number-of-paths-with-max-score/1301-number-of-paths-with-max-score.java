class pair{
    int sum;
    int ways;
    public pair(int sum,int ways){
        this.sum=sum;
        this.ways=ways;
    }
}
class Solution {
    int mod=(int)(1e9+7);
    public pair solve(int i,int j,List<String> board,pair[][] dp){
        if(i<0||j<0){
            return new pair(Integer.MIN_VALUE,0);
        }
        if(i==0&&j==0){
            return new pair(0,1);
        }
        if(dp[i][j].sum!=-1){
            return dp[i][j];
        }
        char ch=board.get(i).charAt(j);
        if(ch=='X'){
            return new pair(Integer.MIN_VALUE,0);
        }
        int x=ch>='0'&&ch<='9'?ch-'0':0;
        pair left=solve(i,j-1,board,dp);
        pair right=solve(i-1,j,board,dp);
        pair dia=solve(i-1,j-1,board,dp);
        int max=Math.max(left.sum,Math.max(right.sum,dia.sum));
        int ways=0;
        int lw=max==left.sum?left.ways:0;
        int rw=max==right.sum?right.ways:0;
        int dw=max==dia.sum?dia.ways:0;
        max=(max+x)%mod;
        ways=(ways+lw+rw+dw)%mod;
        return dp[i][j]=new pair(max,ways);
    }
    public int[] pathsWithMaxScore(List<String> board) {
        int n=board.size();
        int m=board.get(0).length();
        pair[][] dp=new pair[n][m];
        for(pair[] it:dp){
            Arrays.fill(it,new pair(-1,-1));
        }
        pair res=solve(n-1,m-1,board,dp);
        res.sum=Math.max(res.sum,0);
        return new int[]{res.sum,res.ways};
    }
}