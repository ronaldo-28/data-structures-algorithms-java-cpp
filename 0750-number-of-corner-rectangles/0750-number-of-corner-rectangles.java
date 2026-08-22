class Solution {
    public int countCornerRectangles(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] pairCount = new int[cols][cols];
        int rectangle = 0;

        for (int i = 0; i < rows; i++) {
            for (int left = 0; left < cols; left++) {
                if (grid[i][left] == 0) {
                    continue;
                }

                for (int right = left + 1; right < cols; right++) {
                    if (grid[i][right] == 0) {
                        continue;
                    }

                    rectangle += pairCount[left][right];
                    pairCount[left][right]++;
                }
            }
        }

        return rectangle;
    }
}
/**
brute force:
 on row i, how many sides can we make?

 */