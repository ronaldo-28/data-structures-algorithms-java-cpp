class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int maj = nums.length/2;
        for(int i=0; i<nums.length-maj; i++) {
            if(nums[i] == target && nums[i+maj] == target) {
                return true;
            }
        }
        return false;
    }
}