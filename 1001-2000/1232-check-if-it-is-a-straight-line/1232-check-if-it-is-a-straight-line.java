class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if (coordinates.length <= 2) {
            return true;
        }

        int x0 = coordinates[0][0];
        int y0 = coordinates[0][1];
        int x1 = coordinates[1][0];
        int y1 = coordinates[1][1];

        for (int i = 2; i < coordinates.length; i++) {
            int x2 = coordinates[i][0];
            int y2 = coordinates[i][1];

            if ((y1 - y0) * (x2 - x1) != (y2 - y1) * (x1 - x0)) {
                return false;
            }
        }
        return true;
    }
}