class Solution {
    public int minOperations(int[][] grid, int x) {
        int rows = grid.length, cols = grid[0].length;
        int len = rows*cols;
        if (len < 2) return 0;
        int[] freq = new int[10_001];

        for (int row = 0, num = grid[0][0]; row < rows; row++) 
            for (int col = 0; col < cols; col++) {
                if ((num - grid[row][col]) % x != 0) return -1;
                freq[grid[row][col]]++;
            }
        
        int index = (len+1) / 2;
        int median = 0;
        for (int i = 1, j = 0; i < 10_001; i++) {
            if (freq[i] == 0) continue;
            if ((j += freq[i]) >= index) {
                median = i;
                break;
            }
        }

        int operations = 0;
        for (int i = 1; i < 10_001; i++) 
            if (freq[i] != 0)
                operations += freq[i] * (Math.abs(i-median) / x);

        return operations;
    }
}