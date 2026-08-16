class Solution {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int[] intr : intervals) {
            if (max < intr[0]) max = intr[0];
            if (min > intr[0]) min = intr[0];
        }
        int m = max - min + 1;
        int[] startIdx = new int[m];
        for (int i = 0; i < n; i++) startIdx[intervals[i][0] - min] = i + 1;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int end = intervals[i][1];
            if (end > max) {
                res[i] = -1;
                continue;
            }
            while (startIdx[end - min] == 0) end++;
            res[i] = startIdx[end - min] - 1;
        }
        return res;
    }
}