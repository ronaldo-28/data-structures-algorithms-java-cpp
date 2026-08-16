class NumMatrix {

    private final int rows;
    private final int cols;
    private final int[][] matrix;
    private final int[][] bit;

    public NumMatrix(int[][] matrix) {
        rows = matrix.length;
        cols = rows == 0 ? 0 : matrix[0].length;

        this.matrix = new int[rows][cols];
        this.bit = new int[rows + 1][cols + 1];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                update(row, col, matrix[row][col]);
            }
        }
    }

    public void update(int row, int col, int val) {
        int difference = val - matrix[row][col];
        matrix[row][col] = val;

        for (int i = row + 1; i <= rows; i += i & -i) {
            for (int j = col + 1; j <= cols; j += j & -j) {
                bit[i][j] += difference;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefixSum(row2, col2)
                - prefixSum(row1 - 1, col2)
                - prefixSum(row2, col1 - 1)
                + prefixSum(row1 - 1, col1 - 1);
    }

    private int prefixSum(int row, int col) {
        int sum = 0;

        for (int i = row + 1; i > 0; i -= i & -i) {
            for (int j = col + 1; j > 0; j -= j & -j) {
                sum += bit[i][j];
            }
        }

        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */