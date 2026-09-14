class Solution {

    public int minMoves(int[][] rooks) {
        int minMoves = 0;

        // Store the count of rooks in each row and column.
        int[] row = new int[rooks.length];
        int[] col = new int[rooks.length];
        for (int i = 0; i < rooks.length; i++) {
            row[rooks[i][0]]++;
            col[rooks[i][1]]++;
        }

        int rowMinMoves = 0, colMinMoves = 0;
        for (int i = 0; i < rooks.length; i++) {
            // Difference between the rooks count at row and column and one.
            rowMinMoves += row[i] - 1;
            colMinMoves += col[i] - 1;

            // Moves required for row and column constraints.
            minMoves += Math.abs(rowMinMoves) + Math.abs(colMinMoves);
        }

        return minMoves;
    }
}