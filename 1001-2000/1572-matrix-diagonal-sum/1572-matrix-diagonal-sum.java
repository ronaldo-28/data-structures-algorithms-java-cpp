class Solution {
    public int diagonalSum(int[][] mat) {
        int n = mat.length;   // Number of rows (and columns) in the square matrix.
        int sum = 0;          // Variable to accumulate the sum of the diagonals.

        // Step 1: Loop over each row of the matrix.
        for (int i = 0; i < n; i++) {
            // Explanation: Add the element from the primary diagonal.
            sum += mat[i][i];
            // Explanation: Add the element from the secondary diagonal.
            sum += mat[i][n - 1 - i];
        }
        
        // Step 2: If n is odd, subtract the middle element once because it was added twice.
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }
        
        // Step 3: Return the computed sum.
        return sum;
    }
}
