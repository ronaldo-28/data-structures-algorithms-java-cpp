class Solution {
    public long maxArea(int[][] coords) {
        int n = coords.length;
        int minX = (int) 1e6, maxX = 0;
        int minY = (int) 1e6, maxY = 0;

        for (int[] coord : coords) {
            int x = coord[0], y = coord[1];
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        int[] xMinMap = new int[maxX + 1];
        int[] xMaxMap = new int[maxX + 1];

        int[] yMinMap = new int[maxY + 1];
        int[] yMaxMap = new int[maxY + 1];

        for (int[] coord : coords) {
            int x = coord[0], y = coord[1];
            if (xMaxMap[x] == 0) {
                xMaxMap[x] = y;
                xMinMap[x] = y;
            } else {
                if (y > xMaxMap[x])
                    xMaxMap[x] = y;

                if (y < xMinMap[x])
                    xMinMap[x] = y;
            }

            if (yMaxMap[y] == 0) {
                yMaxMap[y] = x;
                yMinMap[y] = x;
            } else {
                if (x > yMaxMap[y])
                    yMaxMap[y] = x;

                if (x < yMinMap[y])
                    yMinMap[y] = x;
            }

        }

        long maxArea = 0;
        for (int[] coord : coords) {
            int x = coord[0], y = coord[1];
            maxArea = Math.max(maxArea, (long) (xMaxMap[x] - xMinMap[x]) * Math.max(x - minX, maxX - x));
            maxArea = Math.max(maxArea, (long) (yMaxMap[y] - yMinMap[y]) * Math.max(y - minY, maxY - y));
        }

        return maxArea == 0 ? -1 : maxArea;
    }
}