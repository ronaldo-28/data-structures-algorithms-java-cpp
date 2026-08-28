class Solution {
    public int[] largestSubarray(int[] nums, int k) {   
        int id = 0;
        int max = nums[0];
        int newNum;
        int size = nums.length - k;
        for (int i = 1; i <= size; i++) {
            newNum = nums[i];
            if (max < newNum) {
                id = i;
                max = newNum;
            }
        }
        int[] result = new int[k];
        System.arraycopy(nums, id, result, 0, k);
        return result;
    }
}