class Solution {
    public long minOperations(int[] nums1, int[] nums2) {
        int n=nums1.length;
        long sum=0;
        //simply take absolute difference cumulative sum
        for(int i=0; i<n; i++)sum+=Math.abs(nums1[i]-nums2[i]);

        long min=Long.MAX_VALUE;
        for(int i=0; i<n; i++){
            long cur=Long.MAX_VALUE;
            //check nums2[n] is lies in between nums1[i] and nums2[i] then at the time of other two operation (+1,-1) when value becomes to nums2[n] then that time we can append that val with cost of 1
            if((nums1[i]>=nums2[n] && nums2[i]<=nums2[n]) || (nums1[i]<=nums2[n] && nums2[i]>=nums2[n]))cur=1;
            //otherwise below will be the cost
            else cur=Math.min(Math.abs(nums2[n]-nums2[i]),Math.abs(nums2[n]-nums1[i]))+1;
            min=Math.min(min,sum+cur);
            if(cur==1)break;//break early extra cost will be minimum 1 
        }
        return min;
    }
}