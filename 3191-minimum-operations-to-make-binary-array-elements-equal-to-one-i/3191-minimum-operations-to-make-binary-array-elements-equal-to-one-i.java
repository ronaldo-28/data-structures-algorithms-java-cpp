class Solution {
    public int minOperations(int[] nums) {
        int operations = 0; // Initialize the count of operations
        int length = nums.length; // Get the length of the array

        // Traverse through the array
        for (int i = 0; i < length; ++i) {
            // If we encounter a 0 in the array
            if (nums[i] == 0) {
                // Check if there's room to flip the next two bits
                if (i + 2 >= length) {
                    return -1; // Return -1 if we can't perform the flip operation
                }
                // Flip the bits at positions i + 1 and i + 2
                nums[i + 1] ^= 1;
                nums[i + 2] ^= 1;
                ++operations; // Increment the operation count
            }
        }
        return operations; // Return the total count of operations needed
    }
}