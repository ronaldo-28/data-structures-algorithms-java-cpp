class Solution {
    public int findMiddleIndex(int[] nums) {
        int sum = 0;
        for(int n : nums){
            sum+=n;
        }
        int currSum = 0;
        for(int i = 0 ; i < nums.length; i++){
            if(2*currSum == (sum - nums[i])){
                return i;
            }
            currSum+=nums[i];
        }   
        return -1;
    }
}