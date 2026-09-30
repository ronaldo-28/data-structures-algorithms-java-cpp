class Solution {
    public int minimumDistance(int[][] points) {
        int maxU1 = Integer.MIN_VALUE, maxU2 = Integer.MIN_VALUE;
        int minU1 = Integer.MAX_VALUE, minU2 = Integer.MAX_VALUE;
        int maxV1 = Integer.MIN_VALUE, maxV2 = Integer.MIN_VALUE;
        int minV1 = Integer.MAX_VALUE, minV2 = Integer.MAX_VALUE;

        int maxUIdx = -1, minUIdx = -1, maxVIdx = -1, minVIdx = -1;

        for (int i = 0; i < points.length; i++) {
            int u = points[i][0] + points[i][1];
            int v = points[i][0] - points[i][1];

            if (u > maxU1) {
                maxU2 = maxU1;
                maxU1 = u;
                maxUIdx = i;
            } else if (u > maxU2) {
                maxU2 = u;
            }

            if (u < minU1) {
                minU2 = minU1;
                minU1 = u;
                minUIdx = i;
            } else if (u < minU2) {
                minU2 = u;
            }

            if (v > maxV1) {
                maxV2 = maxV1;
                maxV1 = v;
                maxVIdx = i;
            } else if (v > maxV2) {
                maxV2 = v;
            }

            if (v < minV1) {
                minV2 = minV1;
                minV1 = v;
                minVIdx = i;
            } else if (v < minV2) {
                minV2 = v;
            }
        }

        int[] candidates = {maxUIdx, minUIdx, maxVIdx, minVIdx};
        int result = Integer.MAX_VALUE;

        for (int skip : candidates) {
            int curMaxU = (skip == maxUIdx) ? maxU2 : maxU1;
            int curMinU = (skip == minUIdx) ? minU2 : minU1;
            int curMaxV = (skip == maxVIdx) ? maxV2 : maxV1;
            int curMinV = (skip == minVIdx) ? minV2 : minV1;

            int dist = Math.max(curMaxU - curMinU, curMaxV - curMinV);
            result = Math.min(result, dist);
        }

        return result;
    }
}