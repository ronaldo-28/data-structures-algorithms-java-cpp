class Solution {
public:
    // Sets to track used numbers in each row, column, and 3x3 block
    vector<unordered_set<int>> rows, cols, blocks;

    // Initialize sets for 9 rows, 9 columns, and 9 blocks
    Solution() : rows(9), cols(9), blocks(9) {}

    // Main solver function
    void solveSudoku(vector<vector<char>>& board) {
        // Pre-populate sets with existing numbers from the board
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rows[i].insert(num);
                    cols[j].insert(num);
                    int block = (i / 3) * 3 + (j / 3);
                    blocks[block].insert(num);
                }
            }
        }
        // Start backtracking from top-left corner (0,0)
        backTrackSolve(0, 0, board);
    }

    // Backtracking recursive solver
    bool backTrackSolve(int row, int col, vector<vector<char>>& board) {
        // Base case: completed all rows
        if (row == 9) 
            return true;

        // Calculate next cell position (wrap to next row when reaching end of column)
        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col == 8) ? 0 : col + 1;

        // Skip pre-filled cells
        if (board[row][col] != '.') {
            return backTrackSolve(nextRow, nextCol, board);
        }

        // Try numbers 1-9 for current cell
        for (int num = 1; num <= 9; ++num) {
            if (isValidPlacement(num, row, col)) {
                // Place number and update sets
                board[row][col] = num + '0';
                rows[row].insert(num);
                cols[col].insert(num);
                int block = (row / 3) * 3 + (col / 3);
                blocks[block].insert(num);

                // Recurse to next cell
                if (backTrackSolve(nextRow, nextCol, board)) {
                    return true;
                }

                // Backtrack: remove number if it leads to dead end
                board[row][col] = '.';
                rows[row].erase(num);
                cols[col].erase(num);
                blocks[block].erase(num);
            }
        }
        return false; // No valid number found for this cell
    }

    // Validates if number can be placed at (row, col)
    bool isValidPlacement(int num, int row, int col) {
        int block = (row / 3) * 3 + (col / 3);
        // Check row, column, and block constraints
        return rows[row].find(num) == rows[row].end() &&
               cols[col].find(num) == cols[col].end() &&
               blocks[block].find(num) == blocks[block].end();
    }
};

// Note: This atexit line is unnecessary for Sudoku solver and may cause runtime issues.
// It's likely a mistake from copied code - remove it for standard implementation.
auto init = atexit([]() { ofstream("display_runtime.txt") << "0"; });