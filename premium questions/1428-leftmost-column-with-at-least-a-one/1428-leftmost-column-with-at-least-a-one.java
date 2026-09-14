/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int numRows = binaryMatrix.dimensions().get(0);
        int numCols = binaryMatrix.dimensions().get(1);

        // corner case
        if (numRows == 0 || numCols == 0) return -1;

        // initialize BST Walker
        int i = 0;
        int j = numCols - 1;
        int result = -1;

        while (i < numRows && j >= 0) {
            int element = binaryMatrix.get(i, j);

            if (element == 1) {
                result = j;
                j--;
            }
            else i++;
        }

        return result;
    }
}