class Solution {
    public int twoEggDrop(int n) {
        int[] dp=new int[3];
        int moves=0;
        while(dp[2] < n){
            moves++;
            for(int i=2;i>0;i--){
                dp[i]=dp[i]+dp[i-1]+1;
            }
        }
        return moves;
    }
}