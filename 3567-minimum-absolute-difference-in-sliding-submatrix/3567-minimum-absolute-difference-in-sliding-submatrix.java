class Solution {
    public int[][] minAbsDiff(int[][] grid, int k) {
        int  m=grid.length;
        int n=grid[0].length;
       int ans[][]=new int[m-k+1][n-k+1];
        int helper[]=new int[k*k];
        for(int i=0;i<m-k+1;i++){
            for(int j=0;j<n-k+1;j++){
                int index=0;
                for(int x=i;x<i+k;x++){
                    for(int y=j;y<j+k;y++){
                    helper[index++]= grid[x][y];
                        
                    }
                }

                Arrays.sort(helper);
                int diff=Integer.MAX_VALUE;
                for(int p=1;p<helper.length;p++){
                    if(helper[p]==helper[p-1]) continue;
                    diff=Math.min(helper[p]-helper[p-1],diff);
                }

                
                ans[i][j]=(diff==Integer.MAX_VALUE)? 0: diff;

            
                
            }
        }

        return ans;
    }
}