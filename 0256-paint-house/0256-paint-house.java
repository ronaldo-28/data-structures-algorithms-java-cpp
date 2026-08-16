class Solution {
    public int minCost(int[][] costs) {
        int lastRed = costs[0][0];
        int lastGreen = costs[0][1];
        int lastBlue = costs[0][2];

        for(int i = 1; i < costs.length; i++){
            int currRed = Math.min(lastGreen, lastBlue) + costs[i][0];
            int currGreen = Math.min(lastRed, lastBlue) + costs[i][1];

            int currBlue = Math.min(lastGreen, lastRed) + costs[i][2];

            lastRed = currRed;
            lastGreen = currGreen;
            lastBlue = currBlue;

        }

        return Math.min(lastRed, Math.min(lastGreen, lastBlue));
    }
}