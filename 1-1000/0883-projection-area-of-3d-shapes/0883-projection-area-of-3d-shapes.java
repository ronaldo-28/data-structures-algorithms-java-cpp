class Solution {
    public int projectionArea(int[][] grid) {
        int total_shadow = 0;
        int length = grid.length;
        for (int i = 0; i < length; i++) {
            total_shadow += getBiggestShadowOfIndex(i, grid, length);
        }

        return total_shadow;
    }

    private static int getBiggestShadowOfIndex(int index, int[][] building, int maxIndex) {
        int maxValueRow = 0;
        int maxValueColumn = 0;
        int currentShadow = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (building[index][i] > 0) {
                currentShadow +=1;
            }
            if (maxValueRow < building[index][i]) {
                maxValueRow = building[index][i];

            }
            if (maxValueColumn < building[i][index]) {
                maxValueColumn = building[i][index];
            }
        }
        return maxValueRow + maxValueColumn + currentShadow;
    }
}