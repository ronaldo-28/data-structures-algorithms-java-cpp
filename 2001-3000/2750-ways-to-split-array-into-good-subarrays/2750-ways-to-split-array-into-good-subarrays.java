class Solution {
    int mod=(int)1e9+7;
    public int numberOfGoodSubarraySplits(int[] nums) {
        int i=0;
        int n=nums.length;
        while(i<n&&nums[i]==0){
            i++;
        }
        if(i==n){
            return 0;
        }
        long ans=1;
        while(i<n){
            i++;
            long cnt=1;
            while(i<n&&nums[i]==0){
                cnt++;
                i++;
            }
            if(i!=n){
                ans=(ans*cnt)%mod;
            }
        }
        return (int)ans;
    }
}