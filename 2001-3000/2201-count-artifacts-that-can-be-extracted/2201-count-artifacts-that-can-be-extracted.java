class Solution {
    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
        boolean[][] flag = new boolean[n][n];
        for(int i = 0; i<dig.length; i++){
            flag[dig[i][0]][dig[i][1]] = true;
        }
    
        int count = 0;
        for(int[] check: artifacts)
            if(isCovered(check, flag)) count++;
        
        return count;
    }
    
    public boolean isCovered(int[] check, boolean [][] flag){
        for(int i = check[0]; i<=check[2]; i++){
            for(int j = check[1]; j <= check[3]; j++){
                if(!flag[i][j]) return false;
            }
        }
        return true;
    }
}