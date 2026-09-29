class Solution {
    public int countIntersectingIntervals(int[][] intervals) {
        int n = intervals.length;
        var freq = new int[101];
        for(var interval : intervals) {
            ++freq[interval[1]];
        }
        
        var prefix = new int[101];
        for(int i=0; i<100; ++i) {
            prefix[i+1] = prefix[i] + freq[i];
        }
        
        long disjoint = 0;
        for(var interval : intervals) {
            disjoint += prefix[interval[0]];
        }
        
        long total = (long)n * (n - 1) >>> 1;
        return (int)(total - disjoint);
    }
}