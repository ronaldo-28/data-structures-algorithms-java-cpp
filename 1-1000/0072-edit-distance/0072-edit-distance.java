class Solution {

    public int solve(String w1, String w2, int i, int j, int dp[][]){

        if(i<0)
            return j+1;

        if(j<0)
            return i+1;

        if(dp[i][j]!=-1)
            return dp[i][j];
        
        if(w1.charAt(i) == w2.charAt(j))
            return dp[i][j] = solve(w1,w2,i-1,j-1,dp);
        
        int insert = solve(w1,w2,i,j-1,dp);
        int delete = solve(w1,w2,i-1,j,dp);
        int replace = solve(w1,w2,i-1,j-1,dp);

        return dp[i][j] = 1+ Math.min(insert, Math.min(delete, replace));


    }
    public int minDistance(String word1, String word2) {
        
        int dp[][] = new int[word1.length()][word2.length()];

        for(int[] rows: dp)
            Arrays.fill(rows, -1);
        
        // if(word2.length()<1 && word1.length()>=1)
        //     return 1;
        return solve(word1, word2, word1.length()-1, word2.length()-1, dp);
    }
}