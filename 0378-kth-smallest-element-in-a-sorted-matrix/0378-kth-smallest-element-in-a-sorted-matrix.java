class Solution {
    public int kthSmallest(int[][] matrix, int K) {
        int N = matrix.length;
        int low = matrix[0][0], high = matrix[N - 1][N - 1];
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countLessEqual(matrix, K, mid)) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    private boolean countLessEqual(int[][] matrix, int K, int X) {
        int N = matrix.length;
        int count = 0;
        for (int i = 0, j = N - 1; i < N; i++) {
            while (j >= 0 && matrix[i][j] > X) j--;
            count += j + 1;
        }
        return count < K;
    }
}