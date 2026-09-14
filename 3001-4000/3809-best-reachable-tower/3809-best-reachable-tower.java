class Solution {
    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int bestX = -1, bestY = -1;   // coordinates of best tower
        int bestQ = -1;               // maximum quality found

        int cx = center[0], cy = center[1]; // center coordinates

        for (int[] t : towers) {
            int x = t[0], y = t[1], q = t[2]; // tower data

            // Manhattan distance calculation (using long to avoid overflow)
            long dx = Math.abs((long) x - cx);
            long dy = Math.abs((long) y - cy);

            // Check if the tower is reachable
            if (dx + dy <= radius) {

                // Update best tower based on:
                // 1) higher quality
                // 2) same quality but lexicographically smaller (x, y)
                if (q > bestQ || (q == bestQ && (bestX == -1 || x < bestX || (x == bestX && y < bestY)))) {
                    bestQ = q;
                    bestX = x;
                    bestY = y;
                }
            }
        }

        // If no reachable tower found, return [-1, -1]
        return bestQ == -1 ? new int[]{-1, -1} : new int[]{bestX, bestY};
    }
}