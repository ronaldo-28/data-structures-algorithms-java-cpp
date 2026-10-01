class Solution {
    public int minRectanglesToCoverPoints(int[][] points, int w) {

        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int count = 0;
        int i = 0;

        while (i < points.length) {

            // Start rectangle at the first uncovered point
            int limit = points[i][0] + w;

            count++;

            // Cover all points that fit
            while (i < points.length &&
                   points[i][0] <= limit) {
                i++;
            }
        }

        return count;
    }
}