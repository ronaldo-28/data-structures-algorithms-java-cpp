class Solution {
    public int maxRotateFunction(int[] nums) {



        int n = nums.length;

        int ans =  Integer.MIN_VALUE;
        int prefix_sum =0;
        for(int i=0;i<n;i++){
            prefix_sum+=nums[i];
        }

        int sum = 0;

        for(int i=0;i<n;i++){
            sum= sum + nums[i]*i;
        }

        ans = Math.max( ans , sum);

        
        for(int i =1; i<n;i++){
            sum = sum + prefix_sum - (n) * nums[n-i]; 
            ans = Math.max( ans , sum);
        }


        return ans;
        
    }
}