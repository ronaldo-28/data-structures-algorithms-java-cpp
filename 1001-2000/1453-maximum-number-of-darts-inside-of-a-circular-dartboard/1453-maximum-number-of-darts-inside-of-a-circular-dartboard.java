class Solution {
    public int numPoints(int[][] darts, int r) {
        int n = darts.length;
        int result = 1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double[] centers = getCenters(darts[i], darts[j], r);
                if (centers != null) {
                    result = Math.max(result, countPoints(darts, centers, r));
                }
            }
        }
        return result;
    }

    // Find circle centers that pass through two points
    private double[] getCenters(int[] p1, int[] p2, int r) {
        double x1 = p1[0], y1 = p1[1];
        double x2 = p2[0], y2 = p2[1];
        double d2 = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
        double d = Math.sqrt(d2);

        if (d > 2.0 * r) return null; // too far apart

        double midX = (x1 + x2) / 2.0;
        double midY = (y1 + y2) / 2.0;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        double h = Math.sqrt(r * r - (d / 2.0) * (d / 2.0));

        double offsetX = h * Math.sin(angle);
        double offsetY = -h * Math.cos(angle);

        return new double[]{midX + offsetX, midY + offsetY, midX - offsetX, midY - offsetY};
    }

    // Count points inside circle centered at (cx, cy)
    private int countPoints(int[][] darts, double[] centers, int r) {
        int maxCount = 0;
        for (int k = 0; k < centers.length; k += 2) {
            double cx = centers[k], cy = centers[k + 1];
            int count = 0;
            for (int[] dart : darts) {
                double dx = dart[0] - cx;
                double dy = dart[1] - cy;
                if (dx * dx + dy * dy <= r * r + 1e-6) {
                    count++;
                }
            }
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}