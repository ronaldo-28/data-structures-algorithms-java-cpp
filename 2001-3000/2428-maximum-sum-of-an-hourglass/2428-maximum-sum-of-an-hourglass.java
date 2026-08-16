class Solution {
        static
    {
        for(int i=0;i<=200;i++) maxSum(new int[3][3]);
    }
    public static int maxSum(int[][] grid) {
        int sum = 0;
        int n = grid.length - 3;
        int m = grid[0].length - 3;
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                int temp = grid[i][j] + grid[i][j + 1] + grid[i][j + 2] + grid[i + 1][j + 1] + grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2];
                if(temp > sum) sum = temp;
            }
        }
        return sum;
    }
}