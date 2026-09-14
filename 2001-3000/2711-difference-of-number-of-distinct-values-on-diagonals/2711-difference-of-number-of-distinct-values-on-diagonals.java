class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int[][] distinct = new int[grid.length][grid[0].length];
        for (int j = grid[0].length - 1; j>=0; j--) {
            populateDistinctGrid(grid, 0, j, distinct);
        }
        for (int i = 1; i < grid.length; i++) {
            populateDistinctGrid(grid, i, 0, distinct);
        }
        return distinct;
    }

    public void populateDistinctGrid(int[][] grid, int row, int col, int[][] distinct) {
        DistinctValues upperValues = new DistinctValues();
        DistinctValues bottomValues = new DistinctValues();
        // count the uniques of whole diagonal, except the first number
        for (int i = row + 1, j = col + 1; i < grid.length && j < grid[0].length; i++, j++) {
            bottomValues.add(grid[i][j]);
        }
        distinct[row][col] = bottomValues.unique;
        // prefix unique: remove current number from bottom and add prev to upper diagonal
        for (int i = row + 1, j = col + 1; i < grid.length && j < grid[0].length; i++, j++) {
            bottomValues.remove(grid[i][j]);
            upperValues.add(grid[i-1][j-1]);
            distinct[i][j] = Math.abs(upperValues.unique - bottomValues.unique);
        }
    }

    private static class DistinctValues {
        int[] count;
        int unique;
        
        public DistinctValues () {
            count = new int[51];
        }

        public void add(int number) {
            if (++count[number] == 1) {
                unique++;
            }
        }

        public void remove(int number) {
            if (--count[number] == 0) {
                unique--;
            }
        }
    }
}