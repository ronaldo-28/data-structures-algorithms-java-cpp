class Solution {
    public int[][] findFarmland(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        List<int[]> res = new ArrayList<>();
        int r = 0;
        int c = 0;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(land[i][j] == 1){
                    if((i == 0 || land[i-1][j] == 0) &&(j == 0 || land[i][j-1] == 0)){
                        r = i;
                        c = j;
                        while(r<n && land[r][j] == 1){
                            r++;
                        }
                        while(c<m && land[i][c] == 1){
                            c++;
                        }
                        res.add(new int[]{i,j,r-1,c-1});
                    }
                    
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}