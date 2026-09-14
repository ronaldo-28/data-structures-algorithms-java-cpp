class Solution {
    public int maxIntersectionCount(int[] y) {
        int n = y.length;
        int[] segmentStart = new int[n - 1];
        int[] segmentEnd = new int[n - 1];

        // Initialize segmentStart and segmentEnd arrays
        for (int i = 0; i < n - 1; i++) {
            int doubleY1 = y[i] * 2;
            int doubleY2 = y[i + 1] * 2;

            if (i > 0 && doubleY1 > doubleY2) {
                doubleY1--;
            } else if (i > 0) {
                doubleY1++;
            }

            if (doubleY1 < doubleY2) {
                segmentStart[i] = doubleY1;
                segmentEnd[i] = doubleY2;
            } else {
                segmentStart[i] = doubleY2;
                segmentEnd[i] = doubleY1;
            }
        }
        // segmentStart:[2,2,3,2,3,4]
        // segmentEnd:[4,3,4,3,6,5]
        
        // Sort segmentStart and segmentEnd arrays
        Arrays.sort(segmentStart);
        Arrays.sort(segmentEnd);
        // segmentStart:[2,2,2,3,3,4]
        // segmentEnd:[3,3,4,4,5,6]

        int maxIntersections = 0;
        int currentIntersections = 0;
        int endIndex = 0;

/**
 * 1    1.5     2   2.5    3
 * _____________
 * ______
 *       _______
 * ______
 *       __________________
 *              ______
 * 
 * 1 , 1, 1, 1.5, 1.5 , 2
 * 1,5, 1.5, 2, 2, 2.5, 3

 start with 1, see if anything is less than 1, nothing ends when 1 is started. so count++;
 then we go to 1.5, nothing ends when 1.5 is started. so count++;
 now we check 2, before 2 starts, check for segments that have ended. so count--;
 keep on going

 */

        // Calculate maximum intersections
        for (int start : segmentStart) {
            while (endIndex < segmentEnd.length && segmentEnd[endIndex] < start) {
                endIndex++;
                currentIntersections--;
            }
            currentIntersections++;
            maxIntersections = Math.max(maxIntersections, currentIntersections);
        }

        return maxIntersections;
    }


}