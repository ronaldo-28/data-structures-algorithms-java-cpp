class Solution {
    public void wiggleSort(int[] nums) {
    for (int i = 1; i < nums.length; ++i) {
        if ((i % 2 == 1) != (nums[i] > nums[i - 1])) {
            int cache = nums[i];
            nums[i] = nums[i-1];
            nums[i-1] = cache;
        }
    }
}

// 125 / 125 test cases passed.
// Status: Accepted
// Runtime: 1 ms
}