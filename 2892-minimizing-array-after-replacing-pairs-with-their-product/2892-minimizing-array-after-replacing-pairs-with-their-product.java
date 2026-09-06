class Solution {
    public int minArrayLength(int[] nums, int k) {
        int size = 1;
        long curr = nums[0];
        //3 2 1 1
        //1 1 2 3
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == 0) return 1;

            if(curr * nums[i] <= k) {
                curr *= nums[i];
            } else {
                curr = nums[i];
                size++;
            }
        }

        return size;
    }
}