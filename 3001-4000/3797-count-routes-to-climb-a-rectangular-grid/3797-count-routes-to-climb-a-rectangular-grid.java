class Solution {
    public int numberOfRoutes(String[] grid, int d) {
        int n = grid.length;
        int m = grid[0].length();
        long MOD = (int)1e9+7;
        
        long[] ways = new long[m];
        long[] verticalWays = new long[m];
        for(int c = 0; c <m; c++) {
            if(grid[n - 1].charAt(c) == '.') {
                verticalWays[c] = 1;
            }
        }
        
        long[] prefix = new long[m + 1];
        for(int i = 0; i <m; i++) {
            prefix[i + 1] = (prefix[i] + verticalWays[i]) % MOD;
        }
        
        for(int c = 0; c <m; c++) {
            if(grid[n - 1].charAt(c) == '#') continue;
            long verticalContribution = verticalWays[c];
            long horizontalContribution = rangedSum(prefix, c - d, c + d, MOD);
            horizontalContribution = (horizontalContribution - verticalWays[c] + MOD) % MOD;
            ways[c] = (verticalContribution + horizontalContribution) % MOD;
        }
        
        for(int r = n - 2; r >=0; r--) {
            long[] prev = ways;
            ways = new long[m];
            long d2 = (long)d * d;
            int maxDist = (int)Math.sqrt(d2 - 1);
            
            long[] prefixBefore = new long[m + 1];
            for(int i = 0; i <m; i++) {
                prefixBefore[i + 1] = (prefixBefore[i] + prev[i]) % MOD;
            }
        
            long[] verticalFromBelow = new long[m];
            for(int c = 0; c <m; c++) {
                if(grid[r].charAt(c) == '.') {
                    verticalFromBelow[c] = rangedSum(prefixBefore, c - maxDist, c + maxDist, MOD);
                }
            }
        
            long[] prefixVerticalFromBelow = new long[m + 1];
            for(int i = 0; i <m; i++) {
                prefixVerticalFromBelow[i + 1] = (prefixVerticalFromBelow[i] + verticalFromBelow[i]) % MOD;
            }
        
            for(int c = 0; c <m; c++) {
                if(grid[r].charAt(c) == '#') continue;
                long vw = verticalFromBelow[c];
                long horizontalContribution = rangedSum(prefixVerticalFromBelow, c - d, c + d, MOD);
                horizontalContribution = (horizontalContribution - verticalFromBelow[c] + MOD) % MOD;
                ways[c] = (vw + horizontalContribution) % MOD;
            }
        }
        
        long res = 0;
        for(long v: ways) {
            res = (res + v) % MOD;
        }
        
        return (int)res;
    }

    private long rangedSum(long[] prefix, int left, int right, long MOD) {
        int l = Math.max(0, left);
        int r = Math.min(prefix.length - 2, right);
        if(l > r) return 0;
        return (prefix[r + 1] - prefix[l] + MOD) % MOD;
    }
}