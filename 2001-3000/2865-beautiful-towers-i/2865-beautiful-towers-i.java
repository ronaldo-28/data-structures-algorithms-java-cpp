class Solution {
    public long maximumSumOfHeights(int[] heights) {
        int n = heights.length;

        int[] q = new int[n];
        int tail = -1;

        long[] lt = new long[n];
        int[] ltx = new int[n];
        q[++tail] = 0;
        for (int i = 1; i < n; i++) {
            lt[i] = lt[i - 1];
            int h = heights[i];
            while (tail >= 0 && heights[q[tail]] > h) {
                lt[i] += (long) (heights[q[tail]] - h) * (ltx[q[tail]] + 1);
                ltx[i] += ltx[q[tail]] + 1;
                tail--;
            }
            q[++tail] = i;
        }

        long[] rt = new long[n];
        int[] rtx = new int[n];
        tail = -1;
        q[++tail] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            rt[i] = rt[i + 1];
            int h = heights[i];
            while (tail >= 0 && heights[q[tail]] > h) {
                rt[i] += (long) (heights[q[tail]] - h) * (rtx[q[tail]] + 1);
                rtx[i] += rtx[q[tail]] + 1;
                tail--;
            }
            q[++tail] = i;
        }
        
        long sum = 0;
        for (int x : heights) {
            sum += x;
        }
        
        long max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, sum - lt[i] - rt[i]);
        }
        return max;
    }
}