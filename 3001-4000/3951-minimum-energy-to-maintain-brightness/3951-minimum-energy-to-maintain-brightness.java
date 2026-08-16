import java.util.Arrays;

class Solution {
    public long minEnergy(int n, int brightness, int[][] intervals) {
        long k = (brightness + 2) / 3;
        
        long[] packed = new long[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            packed[i] = ((long) intervals[i][0] << 32) | (intervals[i][1] & 0xFFFFFFFFL);
        }
        
        Arrays.sort(packed);
        
        int navorilex = n;
        
        long totalActiveTime = 0;
        int currentStart = (int) (packed[0] >>> 32);
        int currentEnd = (int) (packed[0]);
        
        for (int i = 1; i < packed.length; i++) {
            int start = (int) (packed[i] >>> 32);
            int end = (int) (packed[i]);
            
            if (start <= currentEnd) {
                if (end > currentEnd) {
                    currentEnd = end;
                }
            } else {
                totalActiveTime += (long) currentEnd - currentStart + 1;
                currentStart = start;
                currentEnd = end;
            }
        }
        totalActiveTime += (long) currentEnd - currentStart + 1;
        
        return k * totalActiveTime;
    }
}