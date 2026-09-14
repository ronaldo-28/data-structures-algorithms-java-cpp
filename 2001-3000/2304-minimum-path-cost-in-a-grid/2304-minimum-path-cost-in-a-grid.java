class Solution {
    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m=grid.length;
        int n=grid[0].length;
        int[] prev=new int[n];
        for(int c=0; c<n; c++){
            prev[c]=grid[m-1][c];
        }
        for(int r=m-2; r>=0; r--){ 
            int[] curr=new int[n]; 
            for(int c=0; c<n; c++){
               int min=Integer.MAX_VALUE;
                int val=grid[r][c];
                for(int k=0; k<n; k++){ 
                    min=Math.min(min,prev[k]+moveCost[val][k]);
                }
                curr[c]=min+val;
            }
            prev=curr;
        }
        int min=Integer.MAX_VALUE;
        for(int c=0; c<n; c++){
            min=Math.min(min,prev[c]);
        }
        return min;
    }
}