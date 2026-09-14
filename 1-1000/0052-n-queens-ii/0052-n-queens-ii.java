class Solution {
    int count = 0;
    int n;

    public int totalNQueens(int n) {
        this.n = n;
        solve(0, 0, 0, 0);
        return count;
    }

    // row: current row index
    // columns: bitmask for occupied columns
    // diag1: bitmask for occupied main diagonals (row + col)
    // diag2: bitmask for occupied anti diagonals (row - col + n - 1)
    private void solve(int row, int columns, int diag1, int diag2) {
        if (row == n) {
            count++;
            return;
        }

        int availablePositions = ((1 << n) - 1) & ~(columns | diag1 | diag2);
        while (availablePositions != 0) {
            int position = availablePositions & (-availablePositions); // rightmost 1-bit
            availablePositions &= (availablePositions - 1); // remove the rightmost 1-bit

            solve(
                row + 1,
                columns | position,
                (diag1 | position) << 1,
                (diag2 | position) >> 1
            );
        }
    }
}