class Solution {
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        
        int n = locations.length;
        int MOD = 1_000_000_007;
        
        int[][] dp = new int[n][fuel + 1];

        // Start at start city with 0 fuel used
        
        dp[start][0] = 1;

        for (int f = 0; f <= fuel; f++) {
            for (int city = 0; city < n; city++) {

                if (dp[city][f] == 0) continue;

                for (int next = 0; next < n; next++) {

                    if (next == city) continue;

                    int cost = Math.abs(locations[city] - locations[next]);

                    if (f + cost <= fuel) {
                        dp[next][f + cost] =
                                (dp[next][f + cost] + dp[city][f]) % MOD;
                    }
                }
            }
        }

        // Sum all ways reaching finish with <= fuel used
        int ans = 0;
        for (int f = 0; f <= fuel; f++) {
            ans = (ans + dp[finish][f]) % MOD;
        }

        return ans;
    }

    private int dfsMemo(int[] locations, int start, int finish, int fuel,int[][] memo){
        
        if (fuel < 0) return 0;

        if(memo[start][fuel]!=-1)return memo[start][fuel];

        int paths = 0;

        // Count current position if it's finish
        if (start == finish) {
            paths++;
        }

        for(int i=0;i<locations.length;i++){
            if(i!=start){
                int fuelNeed=Math.abs(locations[start]-locations[i]);
                if(fuel-fuelNeed>=0){
                    paths= (paths+dfsMemo(locations,i,finish,fuel-fuelNeed,memo))%1000000007;
                }
            }
        }
        return memo[start][fuel]=paths;
    }
}