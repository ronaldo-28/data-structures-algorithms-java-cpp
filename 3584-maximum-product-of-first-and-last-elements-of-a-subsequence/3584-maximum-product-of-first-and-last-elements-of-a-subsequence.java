class Solution {
    public long maximumProduct(int[] nums, int m) {
        int n = nums.length;
        if(n<m)
            return 0;
        long maxx = nums[0];
        long minn = nums[0];
        long res = 1L*nums[0]*nums[n-1];
        if(m==1){
            for(int x : nums){
                res = Math.max(res,(long)x*(long)x);
            }
            return res;
        }
        
        for(int i = m-1;i<n;i++){
            maxx = Math.max(maxx,nums[i-m+1]);
            minn = Math.min(minn,nums[i-m+1]);
            res = Math.max(res,maxx*nums[i]);
            res = Math.max(res,minn*nums[i]);
            
        }
        return res;
        
    }
}