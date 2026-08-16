class Solution {
    public double minAreaFreeRect(int[][] points) {
        double minArea = Double.MAX_VALUE;
        int n = points.length;
        Set<Integer> pointSet = new HashSet<>();
        for (int[] point : points) {
            pointSet.add(point[0] * 40001 + point[1]);
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i == j) continue;

                int dx1 = points[j][0] - points[i][0];
                int dy1 = points[j][1] - points[i][1];

                for (int k = j + 1; k < n; k++) {
                    if (k == i) continue;

                    int dx2 = points[k][0] - points[i][0];
                    int dy2 = points[k][1] - points[i][1];

                    if (dx1 * dx2 + dy1 * dy2 != 0) continue;

                    int x4 = points[j][0] + dx2;
                    int y4 = points[j][1] + dy2;

                    if (pointSet.contains(x4 * 40001 + y4)) {
                        double area = Math.sqrt((long) dx1 * dx1 + (long) dy1 * dy1) * Math.sqrt((long) dx2 * dx2 + (long) dy2 * dy2);
                        if (area < minArea) {
                            minArea = area;
                        }
                    }
                }

            }
        }
        return minArea == Double.MAX_VALUE ? 0 : minArea;
    }
}