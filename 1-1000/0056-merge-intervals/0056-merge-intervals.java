class Solution {
    public int[][] merge(int[][] intervals) {
       int highestStart = 0;

    for (int[] interval : intervals)
      highestStart = Math.max(highestStart, interval[0]);

    int[] bestEnds = new int[highestStart + 1];

    for (int i = 0; i < intervals.length; i++) {
      int start = intervals[i][0];
      int end = intervals[i][1];
      bestEnds[start] = Math.max(bestEnds[start], end + 1);
    }

    int r = 0;
    int end = -1;
    int intervalStart = -1;

    for (int i = 0; i < bestEnds.length; i++) {
      if (bestEnds[i] != 0) {
        if (intervalStart == -1) {
          intervalStart = i;
        }

        end = Math.max(end, bestEnds[i] - 1);
      }

      if (end == i) {
        // Add to result
        intervals[r++] = new int[] { intervalStart, end };

        // Reset
        intervalStart = -1; 
        end = -1;
      }
    }

    if (intervalStart != -1) {
      intervals[r++] = new int[] { intervalStart, end };
    }

    if (r == intervals.length)
      return intervals;

    int[][] res = new int[r][];

    for (int i = 0; i < r; i++) {
      int[] interval = intervals[i];
      res[i] = interval;
    }

    return res;
 
    }
}