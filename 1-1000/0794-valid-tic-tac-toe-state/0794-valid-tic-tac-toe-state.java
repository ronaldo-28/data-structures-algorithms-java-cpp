class Solution {
    public boolean validTicTacToe(String[] board) {
        int xCount = 0, oCount = 0;
        boolean xWin = false, oWin = false;
        for (int i = 0; i < 3; i++) {
            int rowX = 0, rowO = 0, colX = 0, colO = 0;
            for (int j = 0; j < 3; j++) {
                char rc = board[i].charAt(j);
                char cc = board[j].charAt(i);

                if (rc == 'X') xCount++;
                else if (rc == 'O') oCount++;

                if (rc == 'X') rowX++;
                else if (rc == 'O') rowO++;

                if (cc == 'X') colX++;
                else if (cc == 'O') colO++;
            }

            if (rowX == 3 || colX == 3) xWin = true;
            if (rowO == 3 || colO == 3) oWin = true;
        }

        int diag1X = 0, diag1O = 0, diag2X = 0, diag2O = 0;
        for (int i = 0; i < 3; i++) {
            char c1 = board[i].charAt(i);
            char c2 = board[i].charAt(2 - i);

            if (c1 == 'X') diag1X++;
            else if (c1 == 'O') diag1O++;
            if (c2 == 'X') diag2X++;
            else if (c2 == 'O') diag2O++;
        }
        if (diag1X == 3 || diag2X == 3) xWin = true;
        if (diag1O == 3 || diag2O == 3) oWin = true;
        if (oCount > xCount || xCount - oCount > 1) return false;
        if (xWin && xCount != oCount + 1) return false;
        if (oWin && xCount != oCount) return false;

        return true;
    }
}