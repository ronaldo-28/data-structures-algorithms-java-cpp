class Solution {
    private static final int MOD = 1000000007;
    public int numberOfWays(int n, int[] limit) {
        int y = 0, m = limit.length;
        for(int i = 0; i < m; i++) {
            if(limit[i] >= n) limit[i] = n - 1; //clamp limits to be < n
        }
        Arrays.sort(limit);

        long ans = 0;
        long[] suffix = new long[m + 1];
        for(int i = m - 1; i >= 0; i--) suffix[i] = limit[i] + suffix[i + 1]; //suffix sum
        
        for(int x = m - 1; x >= 0 && limit[x] + limit[m - 1] >= n; x--) { //exit early if there's no more valid pairs
            while(limit[x] + limit[y] < n) y++; //find first valid pair

            if(x >= y) ans = (ans + (suffix[y] - limit[x]) - (n - limit[x] - 1L) * (m - y - 1L)) % MOD;
            else ans = (ans + suffix[y] - (n - limit[x] - 1L) * (m - y)) % MOD;
        }
        return (int)((ans + MOD) % MOD);
    }
}