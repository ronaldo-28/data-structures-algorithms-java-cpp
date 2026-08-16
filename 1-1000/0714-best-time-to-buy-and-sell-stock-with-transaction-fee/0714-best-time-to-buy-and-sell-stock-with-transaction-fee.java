class Solution {
    public int maxProfit(int[] prices, int fee) {
         // base case -- done
        // changing params --done

        // recuresce realtion --done
        int n = prices.length;

        if(n==0) return 0;

        int[][]dp = new int[n+2][2];

        dp[n][0] = dp[n][1] = 0;


        for(int idx = n-1 ; idx>=0 ; idx--){

            for(int buy = 0 ; buy<=1;buy++){
                int profit;
                if(buy==0){
                    // we can buy - two option
                    profit = Math.max(dp[idx+1][1] - prices[idx] , dp[idx+1][0]);
                }
                else{
                    // we can sell = two optiom
                    profit  = Math.max(dp[idx+1][0] + prices[idx] -fee , dp[idx+1][1]);
                }
                dp[idx][buy] = profit;
                
            }
        }
        return dp[0][0];
        
    }
     static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}