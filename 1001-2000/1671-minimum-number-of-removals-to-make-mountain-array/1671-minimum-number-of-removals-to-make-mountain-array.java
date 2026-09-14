class Solution {
    public int minimumMountainRemovals(int[] nums) {
        int[] dp=new int[nums.length];
        int[] dp1=new int[nums.length];
        Arrays.fill(dp,1);
        Arrays.fill(dp1,1);

        int size=0;
        int[] lis = new int[nums.length];
        int[] lds = new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            int l=0;
            int r=size;
            while(l<r) {
                int mid=l+(r-l)/2;
                if(dp[mid] < nums[i]) {
                    l=mid+1;
                }
                else{
                    r=mid;
                }
            }
            dp[l]=nums[i];
            if(l==size) size++;
            lis[i]=l+1;
        }
        size=0;
        for(int i=nums.length-1;i>=0;i--) {
            int l=0;
            int r=size;
            while(l<r) {
                int mid=l+(r-l)/2;
                if(dp1[mid] < nums[i]) {
                    l=mid+1;
                }
                else{
                    r=mid;
                }
            }
            dp1[l]=nums[i];
            if(l==size) size++;
            lds[i]=l+1;
        }
        int max = 0;
        for(int i = 1; i < nums.length - 1; i++) {
            if(lis[i] > 1 && lds[i] > 1) {
                max = Math.max(max, lis[i] + lds[i] - 1);
            }
        }

        return nums.length - max;
    }
}