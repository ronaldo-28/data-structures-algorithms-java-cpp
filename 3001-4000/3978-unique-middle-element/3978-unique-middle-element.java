class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        int n = nums.length;
        int k = nums[n / 2];
        for(int i = 0 ; i < n / 2 ; i++){
            if(nums[i] == k || nums[i + (n / 2) + 1] == k) return false;
        }
        return true;
    }
}