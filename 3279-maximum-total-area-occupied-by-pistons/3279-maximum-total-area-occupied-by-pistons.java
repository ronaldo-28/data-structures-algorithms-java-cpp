class Solution {
    public long maxArea(int height, int[] positions, String directions) {
        int n = positions.length;
        long currentArea = 0;
        int U = 0, D = 0;

        int[] upToDown = new int[2 * height + 1];
        int[] downToUp = new int[2 * height + 1];

        for (int i = 0; i < n; i++) {
            currentArea += positions[i];
            char dir = directions.charAt(i);

            if (dir == 'U') {
                U++;
                int timeToTop = height - positions[i];
                if (timeToTop > 0 && timeToTop <= 2 * height) {
                    upToDown[timeToTop]++;
                }
            } else {
                D++;
                int timeToBottom = positions[i];
                if (timeToBottom > 0 && timeToBottom <= 2 * height) {
                    downToUp[timeToBottom]++;
                }
            }
        }

        long maxArea = currentArea;

        for (int t = 1; t <= 2 * height; t++) {
            // 1. First move 1 unit in the CURRENT direction
            currentArea += (U - D);
            maxArea = Math.max(maxArea, currentArea);

            // 2. NOW process direction flips that occur at time t (for t + 1 onwards)
            if (upToDown[t] > 0) {
                int count = upToDown[t];
                U -= count;
                D += count;
                if (t + height <= 2 * height) {
                    downToUp[t + height] += count;
                }
            }

            if (downToUp[t] > 0) {
                int count = downToUp[t];
                D -= count;
                U += count;
                if (t + height <= 2 * height) {
                    upToDown[t + height] += count;
                }
            }
        }

        return maxArea;
    }
}