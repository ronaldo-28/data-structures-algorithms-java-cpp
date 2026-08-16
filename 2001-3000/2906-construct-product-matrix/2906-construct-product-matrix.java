class Solution {
    public int[][] fillAllWithZeros(int[][] grid){
        for(int[] row : grid) 
            Arrays.fill(row, 0);
        return grid;
    }

    int MOD;
    public int[][] constructProductMatrix(int[][] grid) {
        MOD = 12345;
        int rows = grid.length;
        int cols = grid[0].length;

        int n = rows * cols;
        int[] preProduct = new int[n];

        int cnt0 = 0;
        
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int index = i*cols+j;
                grid[i][j] %= MOD;
                if(grid[i][j] == 0) cnt0++;
                if(cnt0 >= 2) return fillAllWithZeros(grid);
                preProduct[index] = (((index > 0)? preProduct[index-1] : 1) * grid[i][j]) % MOD;
            }
        }

        int[] suffProduct = new int[n];

        for(int i = rows-1; i >= 0; i--){
            for(int j = cols-1; j >= 0; j--){
                int index = i*cols+j;
                suffProduct[index] = (((index < n-1)? suffProduct[index+1] : 1) * grid[i][j]) % MOD;
            }
        }

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                int index = i * cols + j;
                grid[i][j] = (index > 0)? preProduct[index-1] : 1;
                grid[i][j] = (grid[i][j] * ((index < n-1)? suffProduct[index+1] : 1)) % MOD;
            }
        }
        return grid;
    }
}