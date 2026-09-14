class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        final int N = score.length;
        for (int i = 0;i < N;i++) {
            int[] max = findMax(score, i, k);
            if (score[i][k] != max[0]) {
                swap(score, i, max[1]);
            }
        }

        return score;
    }

    int[] findMax(int[][] grid, int start, int k) {
        int max = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = start;i < grid.length;i++) {
            if (max < grid[i][k]) {
                max = grid[i][k];
                idx = i;
            }
        }

        return new int[]{max, idx};
    }

    void swap(int[][] grid, int i, int j) {
        int[] temp = grid[i];
        grid[i] = grid[j];
        grid[j] = temp;
    }
}