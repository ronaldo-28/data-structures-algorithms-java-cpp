class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int min = 0;
        int[] p = points[0];
        for (int i = 1; i < points.length; i++) {
            int[] c = points[i];
            min = min + min(p, c);
            p = c;
        }
        return min;
    }

    public int min(int[] p1, int[] p2) {
        int min = 0;
        int dx = Math.abs(p2[0] - p1[0]);
        int dy = Math.abs(p2[1] - p1[1]);
        if (dx == dy) {
            min = dx;
        } else {
            int d = Math.abs(dx - dy);
            return d + Math.min(dx, dy);
        }
        return min;
    }
}