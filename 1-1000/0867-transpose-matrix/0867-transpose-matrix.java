class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] transposed_matrix = new int[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                transposed_matrix[i][j] = matrix[j][i];
            }
        }
        return transposed_matrix;
    }
}