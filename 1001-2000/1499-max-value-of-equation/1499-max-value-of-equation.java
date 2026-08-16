class Solution {
    public int findMaxValueOfEquation(int[][] points, int k) {
        int n = points.length;
        int max = Integer.MIN_VALUE;

        int left = 0;
        int bestValue = points[0][1] - points[0][0];

        for (int right = 1; right < n; right++) {

            while (left < right && 
                   points[right][0] - points[left][0] > k) {
                left++;
                
                bestValue = Integer.MIN_VALUE;
                for (int t = left; t < right; t++) {
                    bestValue = Math.max(bestValue,
                            points[t][1] - points[t][0]);
                }
            }

            if (left < right) {
                int current = bestValue
                        + points[right][1]
                        + points[right][0];

                max = Math.max(max, current);
            }
            bestValue = Math.max(bestValue,
                    points[right][1] - points[right][0]);
        }

        return max;
    }
}