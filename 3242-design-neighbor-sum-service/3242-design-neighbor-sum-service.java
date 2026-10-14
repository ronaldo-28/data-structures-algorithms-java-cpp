class NeighborSum {
    
   private int[] AdjSum;
   private int[] DiagSum;


    public NeighborSum(int[][] grid) {
       int n=grid.length;
        int maxVal=n*n;
     AdjSum=new int[maxVal];
    DiagSum=new int[maxVal];
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
           int adjSum=0;
            int diagSum=0;
            int val=grid[r][c];
           if(r>0) {
            adjSum+=grid[r-1][c];
           }
           if(r<n-1){
            adjSum+=grid[r+1][c];
           }
           if(c>0){
            adjSum+=grid[r][c-1];
           }
           if(c<n-1){
            adjSum+=grid[r][c+1];
           }

          AdjSum[val]=adjSum;
         

         if(r>0&& c>0){
            diagSum+=grid[r-1][c-1];
         }
         if(r>0 && c<n-1){
            diagSum+=grid[r-1][c+1];
         }
         if(r<n-1 && c>0){
            diagSum+=grid[r+1][c-1];

         }
         if(r<n-1 && c<n-1){
            diagSum+=grid[r+1][c+1];
         }
          DiagSum[val]=diagSum;




            }
        }





    }
    
    public int adjacentSum(int value) {
        return AdjSum[value];
    }
    
    public int diagonalSum(int value) {
       return  DiagSum[value];
    }
}

/**
 * Your NeighborSum object will be instantiated and called as such:
 * NeighborSum obj = new NeighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */