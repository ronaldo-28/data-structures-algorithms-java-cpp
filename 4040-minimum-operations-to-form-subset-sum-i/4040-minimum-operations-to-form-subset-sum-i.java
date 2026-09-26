class Solution {
    static int INF = (int)1e9;
    public int minOperations(int[] nums, int sum) {

        int n = nums.length;

        int[] dp = new int[sum + 1];
        Arrays.fill(dp, INF);

        dp[0] = 0;

        for(int i = 0; i < n; i++){
            int x = nums[i];
            
            int[] nextdp = dp.clone();
            for(int s = 0; s <= sum; s++){
                if(dp[s] == INF) continue;

                int operations = dp[s];
              
                for(int v = x, divide = 0; v > 0; v >>= 1, divide++){
                    if(s + v <= sum)
                        nextdp[s + v] = Math.min(nextdp[s + v], operations + divide);
                }                
               
                for(int v = x, multiple = 0; s + v <= sum; v <<= 1, multiple++){
                    nextdp[s + v] = Math.min(nextdp[s + v], operations + multiple);
                }                
            }
            
            dp = nextdp;
            if(dp[sum] == 0)
                return 0;
        }

        return dp[sum] < INF? dp[sum] : -1;      
    }
}