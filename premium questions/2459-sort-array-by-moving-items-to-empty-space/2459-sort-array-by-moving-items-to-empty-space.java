class Solution {
    int n;
    public int sortArray(int[] nums) {
        n = nums.length;
        return Math.min(sortArray(nums, 0), sortArray(nums, 1));
    }

    private int sortArray(int[] nums, int offset) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // Skip 0 and elems in the correct position
            if (nums[i] == 0 || i == nums[i]-offset) continue;
            cnt++;
            int j = i;
            // Detect cycles: 0 is the exit element
            while (nums[j] > 0) {
                // Mark the element as visited
                nums[j] = -nums[j];
                j = -nums[j] - offset;
                if (j == i) {
                    // Found a cycle
                    cnt++;
                    break;
                }
            }
        }
        // Unmark all elements, back to original array
        for (int i = 0; i < n; i++) nums[i] = Math.abs(nums[i]);
        return cnt;
    }
}