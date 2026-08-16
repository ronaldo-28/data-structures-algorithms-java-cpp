class Solution {
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int minidx=0,maxidx=0;
        int i=0;
        for(int j=indexDifference;j<nums.length;j++){
            if(nums[i]<nums[minidx]){
                minidx=i;
            }
            if(nums[i]>nums[maxidx]){
                maxidx=i;
            }
            if(Math.abs(nums[j]-nums[minidx])>=valueDifference){
                return new int[]{minidx,j};
            }
            if(Math.abs(nums[j]-nums[maxidx])>=valueDifference){
                return new int[]{maxidx,j};
            }
            i++;
        }
        return new int[]{-1,-1};
        
    }
}