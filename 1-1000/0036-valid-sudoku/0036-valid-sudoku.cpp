class Solution {
public:
    bool row(vector<vector<char>>& board, int i, int j) {
        char c = board[i][j];
        for (int n = 0; n < board[i].size(); n++) {
            if (n == j) continue;
            if (board[i][n] == c) return false;
        }
        return true;
    }

    bool col(vector<vector<char>>& board, int i, int j) {
        char c = board[i][j];
        for (int n = 0; n < board.size(); n++) {
            if (n == i) continue;
            if (board[n][j] == c) return false;
        }
        return true;
    }
    bool box(vector<vector<char>>& board, int i, int j) {
        char c = board[i][j];
        int si = (i/3)*3, sj = (j/3)*3;
        for (int r = si; r < si + 3; ++r) {
            for (int c2 = sj; c2 < sj + 3; ++c2) {
                if (r == i && c2 == j) continue;
                if (board[r][c2] == c) return false;
            }
        }
        return true;
    }

    bool isValidSudoku(vector<vector<char>>& board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board[i].size(); j++) {
                if (board[i][j] != '.' && (!row(board, i, j) || !col(board, i, j) || !box(board, i, j)))
                    return false;
            }
        }
        return true;
    }
};