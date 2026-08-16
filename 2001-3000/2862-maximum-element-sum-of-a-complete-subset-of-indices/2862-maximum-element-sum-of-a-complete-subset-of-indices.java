class Solution {
    public long maximumSum(List<Integer> nums) {
        long n = nums.size();
        long ans = 0;
        for(long i = 1; i <= n; i++) {
            long sum = 0;
            for(long j = 1; i * j * j <= n; j++) 
                sum += nums.get((int)(i * j * j - 1));
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}