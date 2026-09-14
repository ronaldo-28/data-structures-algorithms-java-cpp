class Solution {
    public String tictactoe(int[][] moves) {
        // n represents the size of the Tic-Tac-Toe board.
        int n = 3;
        // Arrays to keep track of the scores for each row and column.
        int[] rows = new int[n];
        int[] cols = new int[n];
        // Variables to track the score for the main diagonal and the anti-diagonal.
        int diagonal = 0;
        int antiDiagonal = 0;
        
        // Process each move in the moves array.
        // Player A makes moves at even indices (0, 2, 4, ...) with value +1.
        // Player B makes moves at odd indices (1, 3, 5, ...) with value -1.
        for (int i = 0; i < moves.length; i++) {
            int row = moves[i][0];
            int col = moves[i][1];
            // Determine the value to add based on whose move it is.
            int add = (i % 2 == 0) ? 1 : -1;
            
            // Update row and column counts.
            rows[row] += add;
            cols[col] += add;
            // Update diagonal count if the move is on the main diagonal.
            if (row == col) {
                diagonal += add;
            }
            // Update anti-diagonal count if the move is on the anti-diagonal.
            if (row + col == n - 1) {
                antiDiagonal += add;
            }
            
            // Check if any row, column, or diagonal has an absolute sum equal to n.
            // A sum of 3 means player A wins (since 3 * +1 = 3).
            // A sum of -3 means player B wins (since 3 * -1 = -3).
            if (Math.abs(rows[row]) == n ||
                Math.abs(cols[col]) == n ||
                Math.abs(diagonal) == n ||
                Math.abs(antiDiagonal) == n) {
                return (i % 2 == 0) ? "A" : "B";
            }
        }
        
        // If all moves are played and no winner is found, check if it's a Draw or Pending.
        return moves.length == 9 ? "Draw" : "Pending";
    }
}
