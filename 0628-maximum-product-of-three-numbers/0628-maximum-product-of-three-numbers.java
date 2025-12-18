class Solution {
    private int getMax(int[] nums, int skip1, int skip2) {
        int max = Integer.MIN_VALUE;
        int maxIdx=-1;
        for(int i=0;i<nums.length;i++) {
            if(i==skip1||i==skip2){
                continue;
            }
            if(nums[i] > max) {
                max = nums[i];
                maxIdx=i;
            }
        }
        return maxIdx;
    }
    private int getMin(int[] nums, int skip1, int skip2) {
        int min = Integer.MAX_VALUE;
        int minIdx=-1;
        for(int i=0;i<nums.length;i++) {
            if(i==skip1||i==skip2){
                continue;
            }
            if(nums[i] < min) {
                min = nums[i];
                minIdx=i;
            }
        }
        return minIdx;
    }
    public int maximumProduct(int[] nums) {
        int max1=getMax(nums,-1,-1);
        int max2=getMax(nums,max1,-1);
        int max3=getMax(nums,max1,max2);

        int min1=getMin(nums,-1,-1);
        int min2=getMin(nums,min1,-1);
    

        return Math.max(nums[max1]*nums[max2]*nums[max3], nums[min1]*nums[min2]*nums[max1]);
    }
}