class Solution {
    public int maximumJumps(int[] nums, int target) {
        int N = nums.length;
        int[] dp = new int[N];
        int[] max = new int[N];
        dp[N-1] = 0;
        max[N-1] = 0;
        for (int i=N-2;i>=0;i--){
            int MAX = Integer.MIN_VALUE;
            for (int j=i+1;j<N;j++){
                if (Math.abs(nums[j]-nums[i])<=target   &&  (dp[j]!=0 || j==N-1)){
                        MAX = Math.max(MAX,dp[j]);
                        if (MAX == max[j])break;
                }                
            }
            dp[i] = MAX != Integer.MIN_VALUE ? 1 + MAX : 0;
            max[i] = Math.max(dp[i],max[i+1]);
        }
        return dp[0] == 0 ? -1 : dp[0];
    }
}