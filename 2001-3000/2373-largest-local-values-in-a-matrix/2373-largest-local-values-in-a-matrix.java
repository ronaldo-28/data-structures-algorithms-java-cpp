class Solution {
   static {
        for(int i = 0; i < 500; i++){
            int[][] a = {
				{9,9,8,1},
				{5,6,2,6},
				{8,2,6,4},
				{6,2,2,2}
		    };
            largestLocal(a);
        }
    }
    public static int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] arr = new int[n - 2][n - 2];

        for(int i = 0; i <= n - 3; i++){
            
            for(int j = 0; j <= n - 3; j++){
                int max = 0;
                if(grid [i][j] > max) max = grid[i][j];
                if(grid [i][j+1] > max) max = grid[i][j+1];
                if(grid [i][j+2] > max) max = grid[i][j+2];
                if(grid [i+1][j] > max) max = grid[i+1][j];
                if(grid [i+1][j+1] > max) max = grid[i+1][j+1];
                if(grid [i+1][j+2] > max) max = grid[i+1][j+2];
                if(grid [i+2][j] > max) max = grid[i+2][j];
                if(grid [i+2][j+1] > max) max = grid[i+2][j+1];
                if(grid [i+2][j+2] > max) max = grid[i+2][j+2];
                arr[i][j] = max;
            }
        }
        return arr;
    }
}