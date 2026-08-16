class Solution {
    static {
        for(int i = 0; i < 500; i++) deleteAndEarn(new int[] {1});
    }
    public static int deleteAndEarn(int[] nums) {
        int n = nums.length;
        int max = 0;
        for(int i : nums){
            if(i > max){
                max = i;
            }
        }
        int[] points = new int[max + 1];
        for(int num : nums){
            points[num] += num;
        }
        int[] dp = new int[max + 1];
        dp[1] = points[1];
        for(int i = 2; i < points.length; i++){
            int x = points[i] + dp[i - 2];
            int y = dp[i - 1];
            int res = Math.max(x, y);
            dp[i] = res;
        }
        return dp[max];
    }
}