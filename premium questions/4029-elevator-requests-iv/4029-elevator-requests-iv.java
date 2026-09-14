class Solution {
    static long INF = (long)1e18;
    public long elevatorRequests(int n, int start, int[][] requests){

        if (requests.length == 1) 
            return Math.max(Math.abs(start - requests[0][1]), requests[0][0]); 

        Arrays.sort(requests, (a, b)-> a[1] - b[1]);

        int m = requests.length + 2;
        int[] floors = new int[m];
        int[] times = new int[m];
        m = 0;
        floors[m] = -1;
        times[m++] = 0;    
            
        for (int[] req : requests) {
            int time = req[0], x = req[1];
            floors[m] = x;
            times[m++] = time;
        }

        floors[m] = n;
        times[m++] = 0;           
        
        long[] dpL = new long[m];
        long[] dpR = new long[m];
        Arrays.fill(dpL, INF);
        Arrays.fill(dpR, INF);

        for (int l = 1; l < m - 1; l++) {
            
            int t1 = times[l], x1 = floors[l], x0 = floors[l - 1];
            for (int r = m - 2; r >= l; r--) {
                int t2 = times[r], x2 = floors[r], x3 = floors[r + 1];
                if (l == 1 && r == m - 2) { // 当前请求是第一个请求
                    // 从 start 到当前楼层
                    dpL[r] = Math.max(Math.abs(x1 - start), t1);
                    dpR[r] = Math.max(Math.abs(x2 - start), t2);
                    continue;
                }
                
                // 比较从floors[l-1]和从floors[r+1]来到floors[r]
                long time = Math.min(dpL[r] + x2 - x0, dpR[r + 1] + x3 - x2);
                dpR[r] = Math.max(time, t2);
                
                // 比较从floors[l-1]和从floors[r+1]来到floors[l]
                time =  Math.min(dpL[r] + x1 - x0, dpR[r + 1] + x3 - x1);
                dpL[r] = Math.max(time, t1);
            }
        }

        long ans = INF;
        for (int i = 1; i < m - 1; i++) 
            ans = Math.min(ans, dpL[i]);
        
        return ans;                
    }
}