class Solution {

    int ballDropper(int[][] grid, int i, int j) {

        int m = grid.length;
        int n = grid[0].length;

        if (i == m) return j;

        if (j < 0 || j > n) {
            return -1;
        }

        if (j >= 0 && j < n - 1 && grid[i][j] == 1 && grid[i][j+1] == -1) {
            // System.out.println("Wrong at i: " + i + " and j: " + j + " grid[i][j] = " + grid[i][j]);
            return -1;
        }
        else if (j > 0 && j <= n - 1 && grid[i][j] == -1 && grid[i][j-1] == 1) {
            // System.out.println("Wrong at i: " + i + " and j: " + j + " grid[i][j] = " + grid[i][j]);
            return -1;
        }
        else if (j == 0 && grid[i][j] == -1) {
            // System.out.println("Wrong at i: " + i + " and j: " + j + " grid[i][j] = " + grid[i][j]);
            return -1;
        }
        else if (j == n - 1 && grid[i][j] == 1) {
            // System.out.println("Wrong at i: " + i + " and j: " + j + " grid[i][j] = " + grid[i][j]);
            return -1;
        }

        if (grid[i][j] == 1) {
            // System.out.println("Proceeding right wtih i: " + i + " and j: " + j + " grid[i][j] = " + grid[i][j]);
            return ballDropper(grid, i + 1, j + 1);
        }
        else {
            // System.out.println("Proceeding left wtih i: " + i + " and j: " + j + " grid[i][j] = " + grid[i][j]);
            return ballDropper(grid, i + 1, j - 1);
        }


    }

    public int[] findBall(int[][] grid) {
        int[] arr = new int[grid[0].length];

        for(int i = 0; i < grid[0].length; i++) {
            arr[i] = ballDropper(grid, 0, i);
        }

        // System.out.println(ballDropper(grid, 0, 5 ));

        return arr;
    }
}