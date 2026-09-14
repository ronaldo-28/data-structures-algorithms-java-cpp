class Solution {
    private static final int[][] moves = 
                        { {-1,+2}, {-2,+1}, {-2,-1}, {-1,-2}, 
                          {+1,-2}, {+2,-1}, {+2,+1}, {+1,+2} };
    private static final int MAX_SIZE = 5;
    private static final int[][][][][] nextMoves = 
                        new int[MAX_SIZE + 1][MAX_SIZE + 1][][][];
    private int[][] board;
    private int[][][] curMoves;
    private int cellMax;

    
    public int[][] tourOfKnight(int m, int n, int r, int c) {
        // Get the list of moves for each board square.  Create the board, 
        // then call the DFS recursion routine to try to find a path that  
        // uses all the board squares exactly once.
        curMoves = getCurMoves(m, n);

        board = new int[m][n];
        for (int r1 = 0; r1 < m; r1++)
            for (int c1 = 0; c1 < n; c1++)
                board[r1][c1] = -1;

        cellMax = m * n;
        if (cellMax == 1)  return new int[][]{{0}};

        dfs(r, c, 0);
        return board;
    }
    
    
    // Recursion routine to do a Depth First Search (DFS) for a currect 
    // path to all squares of the board.  Backtrack when can't move to 
    // all board squares exactly once, and try other moves for squares.
    private boolean done = false;
    private void dfs (int r, int c, int moveNum) {
        board[r][c] = moveNum++;
        if (moveNum == cellMax)
            done = true;
        else {
            for (int move : curMoves[r][c]) {
                int moveRow = (move >> 8) & 0xF;    // Get row for move.
                if (board[moveRow][move & 0xF] < 0) {   // If square not used...
                    dfs(moveRow, move & 0xF, moveNum);
                    if (done)  return;
                }
            }
            board[r][c] = -1;
        }
        return;
    }

    
    // Get cached moves for each square, or build a new grid of moves 
    // for each square.  This creates a grid in curMoves[][] the same 
    // size as the board.  In each square of curMoves[][], create a 
    // list of valid moves that start from that square.  Using this 
    // pre-calculated list of moves, saves time from testing ALL 
    // possible relative moves from a cell, some of which may be 
    // invalidly off the board.  And saves time from repeatedly 
    // using relative moves, by saving only valid absolute moves 
    // in curMoves[][].
    //
    // The contents of curMoves[row][col] is a sorted list of valid 
    // moves from the board square row,col.  Each move in the sorted 
    // list of valid moves, is an int containing:
    //
    //       (warnsdorfCnt << 16) + (toRow << 8) + toCol
    //
    // The toRow and toCol values are the row number (0..m-1) and 
    // column number (0..n-1) for the move, as absolute row and 
    // column numbers, instead of relative move distances.  The 
    // warnsdorffsCnt is the number of moves that can be made from 
    // square toRow,toCol, or equivalently a measure of how hard 
    // it is to get to square toRow,toCol with lower numbers 
    // indicating harder to get to.  The warnsdorffsCnt value is only 
    // used for sorting the list of moves to try to have most optimal 
    // moves appearing first in the list.
    //
    // With the list of valid moves for each square, use Warnsdorff's 
    // Rule for deciding the order of the moves, and sort the lists 
    // of moves.  Three equivalent ways of thinking about Warnsdorff's 
    // Rule are:
    //
    //   1)  In Warnsdorff's Rule, choose the moves first that have 
    //       the fewest number of valid next moves, if that move 
    //       were taken.  For example, if there are two possible 
    //       moves from a square, and one of those moves would be 
    //       to a square that has 3 possible moves, and the other 
    //       move is to a square that has 8 possible moves, then 
    //       try the 3 possible move square before trying the 8 
    //       possible move square.
    //
    //   2)  Warnsdorff's Rule is also equivalent to sorting the list 
    //       of moves for each square, so that the moves to squares 
    //       that have the fewest number of ways to get to that 
    //       square come first in the list of possible moves.  
    //
    //   3)  Warnsdorff's Rule is also equivalent to sorting the 
    //       list of possible moves for a square, so that the moves 
    //       to squares that are the hardest to get to, come first 
    //       in the list of possible moves.
    //
    // After going to all the work of building all possible moves 
    // and sorting them according to Warnsdorff's Rule for each cell 
    // on an m x n board, save those possible moves as cached data 
    // in nextMoves[][], so it can be re-used the next time we have 
    // a board of the same size.
    private int[][][] getCurMoves(int m, int n) {
        int[][][] curMoves = nextMoves[m][n];
        if (curMoves == null) {
            curMoves = new int[m][n][];
            int[][] moveCounts = new int[m][n];
            
            // Count the number of moves from each square.  Use the 
            // counts to allocate array of moves for each square.
            for (int[] move : moves) {
                int deltaRow = move[0];
                int deltaCol = move[1];
                int rowLimit = Math.min(m, m - deltaRow);
                int colLimit = Math.min(n, n - deltaCol);
                for (int r2 = Math.max(0 , -deltaRow); r2 < rowLimit; r2++) {
                    for (int c2 = Math.max(0, -deltaCol); c2 < colLimit; c2++) {
                        moveCounts[r2][c2]++;
                    }
                }
            }
            for (int r2 = 0; r2 < m; r2++) 
                for (int c2 = 0; c2 < n; c2++) 
                    curMoves[r2][c2] = new int[moveCounts[r2][c2]];
            
            // Create the list of possible moves from each square.
            for (int[] move : moves) {
                int deltaRow = move[0];
                int deltaCol = move[1];
                int rowLimit = Math.min(m, m - deltaRow);
                int colLimit = Math.min(n, n - deltaCol);
                for (int r2 = Math.max(0, -deltaRow); r2 < rowLimit; r2++) {
                    for (int c2 = Math.max(0, -deltaCol); c2 < colLimit; c2++) {
                        curMoves[r2][c2][--moveCounts[r2][c2]] = 
                                ((r2 + deltaRow) << 8) + c2 + deltaCol;
                    }
                }
            }
            
            // Use Warnsdorff's Rule to sort the list of possible moves 
            // from this square. 
            for (int r2 = 0; r2 < m; r2++) {
                for (int c2 = 0; c2 < n; c2++) {
                    int[] moves = curMoves[r2][c2];
                    for (int i = curMoves[r2][c2].length - 1; i >= 0; i--) 
                        moves[i] += (curMoves[(moves[i] >> 8) & 0xF][moves[i] & 0xF].length << 16);
                    Arrays.sort(moves);
                }
            }
            
            // Save this list of possible moves for future test cases 
            // that use the same board size.
            nextMoves[m][n] = curMoves;
        }
        return curMoves;
    }
}