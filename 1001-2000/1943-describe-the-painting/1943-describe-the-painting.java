class Solution {
    public List<List<Long>> splitPainting(int[][] segments) {
        int maxLen = 0;
        for (int[] segment : segments) {
            int end = segment[1];
            maxLen = Math.max(maxLen, end);
        }

        long[] diff = new long[maxLen+1];
        boolean[] events = new boolean[maxLen+1];

        for (int[] segment : segments) {
            int start = segment[0];
            int end = segment[1];
            int color = segment[2];
            diff[start] += color;
            diff[end] -= color;
            events[start] = true;
            events[end] = true;
        }

        long currMix = 0;
        int start = -1;
        List<List<Long>> res = new ArrayList<>();
        for (int i=0; i<diff.length; i++) {
            if (!events[i]) continue;
            if (start != -1 && currMix > 0) {
                res.add(List.of((long) start, (long) i, currMix));
            }
            currMix += diff[i];
            start = i;
        }
        return res;
    }
}