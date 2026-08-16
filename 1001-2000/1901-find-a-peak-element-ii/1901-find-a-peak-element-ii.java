class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int left = 0, right = mat[0].length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int maxRow = 0;
            for (int i = 0; i < mat.length; i++) {
                if (mat[i][mid] > mat[maxRow][mid]) 
                    maxRow = i;
            }
            int leftVal = (mid - 1 >= 0) ? mat[maxRow][mid - 1] : -1;
            int rightVal = (mid + 1 < mat[0].length) ? mat[maxRow][mid + 1] : -1;
            if (mat[maxRow][mid] > leftVal && mat[maxRow][mid] > rightVal) 
                return new int[]{maxRow, mid};
            else if (leftVal > mat[maxRow][mid]) 
                right = mid - 1;
             else 
                left = mid + 1;
        }
        return new int[]{-1, -1};
    }
}