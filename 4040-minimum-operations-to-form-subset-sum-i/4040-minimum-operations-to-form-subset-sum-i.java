
class Solution {
    record State(int target, int cost) {}; 
    public int minOperations(int[] nums, int sum) {
        // replace with 2 * x or x / 2 
        // all multiplication before division 
        // min no of ops needed so that some subset has sum exactly equal to sum. 


        // n = 100, sum = 5000, nums[i] = 500 

        // for each position, try to perform all ops, first will be 2*x then simply x/2 and so on... And check what som we can get with all these untill 5000 

        // this is knapsack style DP 
        // we see at each position what can be the value after applying ops and what will be cost associated with it. 


        // since we only care about prev col only, we can have 1-D dp 
        int n = nums.length; 
        int dp[] = new int[sum + 1]; 
        // Initially no of steps for each sum will be inf 
        int inf = Integer.MAX_VALUE; 
        Arrays.fill(dp, inf); 
        dp[0] = 0; // base case

        for(int i = 0; i < n; i++) {
            int cur = nums[i]; 
            List<State> cost = new ArrayList<>(); // we find cost of each valid value that can be formed

            int ops = 0; 
            // multiply first 
            while(cur <= sum) {
                cost.add(new State(cur, ops)); 
                cur = cur << 1; // cur = 2 * cur 
                ops++; 
            }

            cur = nums[i]; 
            // division ops, we can simply do division on nums[i] - as 2 * x/ 2 = x so ops will increase only for a sum 
            // assume 2 -> 4 -> 8 -> 4 again after division so for 4, ops increase only if we came back hence either we multiply or divide. 
            ops = 0; 
            while(cur >= 1) {
                if(cur <= sum) cost.add(new State(cur, ops)); 
                cur = cur >> 1; // cur = cur / 2 
                ops++; 
            }

            // Now for all numbers that is reachable using them what sum can I form?? 

            int next[] = dp.clone();  

            for(int s = 0; s <= sum; s++) {
                if(dp[s] == inf) continue; // s sum can not be formed 

                // else for this sum, iterate over each sum's for ops on current no. 

                for(State c: cost) {
                    int tar = c.target; 
                    ops = c.cost; 

                    if(s + tar <= sum) next[s + tar] = Math.min(next[s + tar], dp[s] + ops); 
                }
            }
            dp = next.clone(); 
        }

        return dp[sum] == inf ? -1 : dp[sum]; 

    }
}