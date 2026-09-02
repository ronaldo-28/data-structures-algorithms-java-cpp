class Solution {
    public long minCost(int[] nums, int[] costs) {
        int n = nums.length;

        int[] stack1 = new int[n], stack2 = new int[n]; //monotonic stacks, one increasing, one decreasing
        int top1 = 0, top2 = 0; //topX = current index for stack X, decrement topX to pop elements

        long[] dp = new long[n]; //dp[i] = the min cost to reach index 'i'

        for(int i = 1; i < n; i++) {
            int num = nums[i], cost = costs[i];
            long current = Long.MAX_VALUE; //current = min cost to reach index 'i'

            //as we pop from the stacks, we update current = min(current, dp[topOfStack] + cost)
            while(top1 >= 0 && num < nums[stack1[top1]]) current = Math.min(current, cost + dp[stack1[top1--]]);
            while(top2 >= 0 && num >= nums[stack2[top2]]) current = Math.min(current, cost + dp[stack2[top2--]]);

            stack1[++top1] = stack2[++top2] = i; //push 'i' to both stacks

            dp[i] = current;
        }

        return dp[n - 1]; //return the final cost
    }
}