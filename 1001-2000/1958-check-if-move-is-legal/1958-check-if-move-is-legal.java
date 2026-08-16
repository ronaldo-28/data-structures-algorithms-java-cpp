class Solution {
    /**
    ["B","W",".","B","W","W","B","."],
    ["B",".",".","B","W","W",".","."],
    ["W","W",".","B","B",".","B","W"],
    ["B","W","B",".","B",".","B","B"],
    ["B","W","W","B",".","W","B","B"],
    ["W","W",".","B","W","B",".","."],
    ["W",".","B","W","W","B",".","B"],
    ["W",".","B","B",".","B",".","."]]
     */
    public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
        board[rMove][cMove] = color;
        return checkUp(board, rMove, cMove, color) || checkDown(board, rMove, cMove, color) || checkLeft(board, rMove, cMove, color) || checkRight(board, rMove, cMove, color) || checkUpDia(board, rMove, cMove, color) || checkDownDia(board, rMove, cMove, color) || checkUpDiaRight(board, rMove, cMove, color) || checkDownDiaLeft(board, rMove, cMove, color);
    }

    public boolean checkUp(char[][] board, int rMove, int cMove, char color){
        if(rMove > 1){
            int r = rMove - 1;
            int c = cMove;
            if(board[r][c] == color){
                return false;
            }
            while(r >= 0 && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                r--;
            }
            if(r < 0 || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkDown(char[][] board, int rMove, int cMove, char color){
        int n = board.length;
        if(n - rMove > 2){
            int r = rMove + 1;
            int c = cMove;
            if(board[r][c] == color){
                return false;
            }
            while(r < n && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                r++;
            }
            if(r == n || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkLeft(char[][] board, int rMove, int cMove, char color){
        if(cMove > 1){
            int r = rMove;
            int c = cMove - 1;
            if(board[r][c] == color){
                return false;
            }
            while(c >= 0 && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                c--;
            }
            if(c < 0 || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkRight(char[][] board, int rMove, int cMove, char color){
        int n = board.length;
        if(n - cMove > 2){
            int r = rMove;
            int c = cMove + 1;
            if(board[r][c] == color){
                return false;
            }
            while(c < n && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                c++;
            }
            if(c == n || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkUpDia(char[][] board, int rMove, int cMove, char color){
        if(rMove > 1 && cMove > 1){
            int r = rMove - 1;
            int c = cMove - 1;
            if(board[r][c] == color){
                return false;
            }
            while(r >= 0 && c >= 0 && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                r--;
                c--;
            }
            if(r < 0 || c < 0 || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkDownDia(char[][] board, int rMove, int cMove, char color){
        int n = board.length;
        if(n - rMove > 2 && n - cMove > 2){
            int r = rMove + 1;
            int c = cMove + 1;
            if(board[r][c] == color){
                return false;
            }
            while(r < n && c < n && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                r++;
                c++;
            }
            if(r == n || c == n || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkUpDiaRight(char[][] board, int rMove, int cMove, char color){
        int n = 8;
        if(rMove > 1 && n - cMove > 2){
            int r = rMove - 1;
            int c = cMove + 1;
            if(board[r][c] == color){
                return false;
            }
            while(r >= 0 && c < n && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                r--;
                c++;
            }
            if(r < 0 || c == n || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean checkDownDiaLeft(char[][] board, int rMove, int cMove, char color){
        int n = board.length;
        if(n - rMove > 2 && cMove > 1){
            int r = rMove + 1;
            int c = cMove - 1;
            if(board[r][c] == color){
                return false;
            }
            while(r < n && c >= 0 && board[r][c] != color){
                if(board[r][c] == '.'){
                    return false;
                }
                r++;
                c--;
            }
            if(r == n || c < 0 || board[r][c] == '.'){
                return false;
            }
            return true;
        }
        return false;
    }
}