class Solution {
    public int[] twoSum(int[] nums, int target) {
          int[] result=new int[2];
        for(int i=1;i<nums.length;i++){
            for(int j=i;j<nums.length;j++){
                if(nums[j]+nums[j-i]==target){
                    result[0]=j-i;
                    result[1]=j;
                    return result;
                }
            }

        }
        return result;
    }
}