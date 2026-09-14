class Solution {
    public int maxSizeSlices(int[] slices) {
        int n = slices.length;
        return Math.max(maxSum(slices, 0, n - 2), maxSum(slices, 1, n - 1));
    }

    private int maxSum(int[] slices, int start, int end) {
        int n = end - start + 1;
        int take = (n + 1) / 3; 
        
        int[] dpPrev = new int[take + 1];
        int[] dpCurr = new int[take + 1];

        for (int i = start; i <= end; i++) {
            int[] dpNext = new int[take + 1];
            for (int j = 1; j <= take; j++) {
                dpNext[j] = Math.max(dpCurr[j], dpPrev[j - 1] + slices[i]);
            }
            dpPrev = dpCurr;
            dpCurr = dpNext;
        }

        return dpCurr[take];
    }
}