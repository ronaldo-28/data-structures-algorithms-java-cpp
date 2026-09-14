class Solution {
    public int[][] generateMatrix(int n) {
        // initialize the matrix, the boundaries, and the number
        int[][] matrix = new int[n][n];
        int top = 0, bottom = n - 1;
        int left = 0, right = n - 1;
        int num = 1; // the current element to be filled
        // loop until the boundaries cross
        while (top <= bottom && left <= right) {
            // fill the elements from left to right
            for (int i = left; i <= right; i++) {
                matrix[top][i] = num++;
            }
            // increment the top boundary
            top++;
            // fill the elements from top to bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            // decrement the right boundary
            right--;
            // fill the elements from right to left
            for (int i = right; i >= left; i--) {
                matrix[bottom][i] = num++;
            }
            // decrement the bottom boundary
            bottom--;
            // fill the elements from bottom to top
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = num++;
            }
            // increment the left boundary
            left++;
        }
        // return the matrix
        return matrix;
    }
}
