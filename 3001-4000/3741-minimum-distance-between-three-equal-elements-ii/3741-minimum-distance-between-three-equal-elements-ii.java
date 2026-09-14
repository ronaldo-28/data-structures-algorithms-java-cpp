class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;
        if(n <= 2) return -1;

        int[] prev = new int[n+1], dp = new int[n+1];
        int mini = Integer.MAX_VALUE;
        for(int right=0; right<n; right++){
            int num = nums[right], left = prev[num], current = dp[num];
            
            if(left != 0){
                mini = Math.min(mini, right - left + 1);
            }

            prev[num] = current;
            dp[num] = right+1;
        }

        return mini == Integer.MAX_VALUE ? -1 : 2*mini;
    }
}