class Solution {
    public int minDayskVariants(int[][] points, int k) {
        int l = 0;
        int r = 100;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (chk(points, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public boolean chk(int[][] points, int k, int mid) {
        int[][] grid = new int[101][101];
        for (int[] point : points) {
            int lX = Math.max(point[0] - mid, 0);
            int rX = Math.min(point[0] + mid, 100);
            for (int i = lX; i <= rX; i++) {
                int len = mid - Math.abs(point[0] - i);
                int lY = Math.max(point[1] - len, 0);
                int rY = Math.min(point[1] + len, 100);
                for (int j = lY; j <= rY; j++) {
                    if (++grid[i][j] == k)
                        return true;
                }
            }
        }
        return false;
    }
}