class Solution {
    public int[] findRightInterval(final int[][] intervals) {
        final int n = intervals.length;
        final int[][] starts = new int[n][2];

        for(int i = 0; i < n; ++i) {
            starts[i][0] = intervals[i][0];
            starts[i][1] = i;
        }

        Arrays.sort(starts, (a, b) -> Integer.compare(a[0], b[0]));

        final int[] res = new int[n];

        for(int i = 0; i < n; ++i) {
            final int end = intervals[i][1], idx = binarySearch(starts, end);
            
            res[i] = (idx == n) ? -1 : starts[idx][1];
        }

        return res;
    }

    private int binarySearch(int[][] starts, int target) {
        int left = 0, right = starts.length;

        while(left < right) {
            final int mid = left + (right - left) / 2;

            if(starts[mid][0] >= target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}