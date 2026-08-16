class Solution {
    public int maxSumMinProduct(int[] nums) {

        int mod = 1_000_000_007 ;

        int n = nums.length ;

        long[] preSum = new long[n + 1] ;
        for(int i = 0 ; i < n ; i ++)
        {
            preSum[i + 1] = preSum[i] + nums[i] ;
        }

        int[] stack = new int[n + 1] ;
        int top = -1 ;

        long res = 0 ;

        for(int i = 0 ; i <= n ; i ++)
        {
            while(top >= 0 && (i == n || nums[stack[top]] > nums[i]))
            {
                int min = nums[stack[top --]] ;

                int prevIdx = top < 0 ? 0 : stack[top] + 1 ;

                res = Math.max(res, 1L * min * (preSum[i] - preSum[prevIdx])) ;
            }
            stack[++ top] = i ;

        }
        return (int)(res % mod) ;
    }
}        