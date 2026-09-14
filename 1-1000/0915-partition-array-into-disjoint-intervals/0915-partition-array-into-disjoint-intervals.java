class Solution {
    public int partitionDisjoint(int[] nums) {
        int possibleMax = nums[0];
        int currenMax = nums[0];
        int length = 1;

        for(int i=1; i<nums.length; i++){
            if(nums[i] < currenMax){
                length = i+1;
                currenMax = possibleMax;
            }else{  
                possibleMax = Math.max(possibleMax, nums[i]);
            }
        }
        return length;
    }
}