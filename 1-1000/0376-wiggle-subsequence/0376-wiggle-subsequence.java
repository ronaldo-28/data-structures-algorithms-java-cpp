class Solution {
    public int wiggleMaxLength(int[] nums) {
         if (nums.length < 2) {
            return nums.length; // If 0 or 1 element, just return length
        }

        int up = 1; // Length of subsequence ending with an increasing wiggle
        int down = 1; // Length of subsequence ending with a decreasing wiggle

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
              up = down + 1; // if up, previous must be down
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1; // if down, previous must be up
            }
            // if they are equal, we don't update and that's okay
        }

        return Math.max(up, down); // Maximum length from both ends.
    }
}