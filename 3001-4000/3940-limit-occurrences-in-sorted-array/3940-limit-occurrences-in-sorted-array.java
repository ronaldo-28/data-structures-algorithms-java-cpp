class Solution {
    public int[] limitOccurrences(int[] nums, int k) {
        if(nums.length <= k)
            return nums;

        int index = k;
        for(int i = k; i < nums.length; i++){
            if(nums[i] != nums[index - k])
                nums[index++] = nums[i];
        }

        return Arrays.copyOf(nums,index);
    }
}