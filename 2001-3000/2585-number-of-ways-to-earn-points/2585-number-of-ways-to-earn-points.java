class Solution {
    public int waysToReachTarget(int target, int[][] types) {
        //return time_optimized(target,types);
        //return space_optimized(target,types);
        return space_time_optimized(target,types);

    }

    int space_time_optimized(int target, int[][] types) {
        var MOD = 1_000_000_007;
        var dp = new long[target + 1];
        dp[0] = 1;

        for (var type : types) {
            var count = type[0];
            var value = type[1];
            var old = dp.clone(); // snapshot before this type

            for (var p = value; p <= target; p++) {
                // unbounded add
                dp[p] += dp[p - value];
                // correction                           
                if (p >= (count + 1) * value)
                    dp[p] -= old[p - (count + 1) * value];       
                dp[p] = ((dp[p] % MOD) + MOD) % MOD;
            }
        }

        return (int) dp[target];
    }

    int space_optimized(int target, int[][] types) {
        var n = types.length;
        var MOD = 1_000_000_007;
        var dp = new long[target+1];
        dp[0]=1;

        for(int[] type: types){
            var value = type[1];
            var count = type[0];

            for(var curr_target= target;curr_target>0;curr_target--){
                for(var considered=1;considered<=count;considered++){
                    if(curr_target-value*considered>=0)
                        dp[curr_target] += dp[curr_target-value*considered];
                        dp[curr_target]%=MOD;
                }
            }
        }

        
        return (int) dp[target];
    }



    int time_optimized(int target, int[][] types) {
        var n = types.length;
        var MOD = 1_000_000_007;
        var dp = new long[target+1][n+1];
        Arrays.fill(dp[0],1);
        for(var points=1;points<=target;points++){
            for(var coin = 1;coin<=n;coin++){
                var value = types[coin-1][1];
                var count = types[coin-1][0];

                dp[points][coin] = dp[points][coin-1];
                if(points-value>=0){
                    dp[points][coin] += dp[points-value][coin];
                }
                if(points>=value*(count+1)){
                    dp[points][coin]-= dp[points-value*(count+1)][coin-1];
                }
                dp[points][coin] = ((dp[points][coin] % MOD) + MOD) % MOD;
            }
        }
        
        return (int) dp[target][n];
    }
}