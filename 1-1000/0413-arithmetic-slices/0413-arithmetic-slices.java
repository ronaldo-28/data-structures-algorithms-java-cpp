class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int arithmeticSliceCount = 0;  // To store the number of arithmetic slices found.
        int currentSliceLength = 0;    // To keep track of the current sequence length.
        int difference = 3000;         // Initialize with a value outside the problem constraints.

        // Iterate through the given array to find arithmetic slices.
        for (int i = 0; i < nums.length - 1; ++i) {
            // Check if the current pair of elements continue the arithmetic sequence.
            if (nums[i + 1] - nums[i] == difference) {
                // If they do, increment the count of arithmetic slices by increasing the current length.
                ++currentSliceLength;
            } else {
                // If not, update the common difference to the new pair's difference.
                difference = nums[i + 1] - nums[i];
                // Reset the current sequence length because a new arithmetic sequence starts.
                currentSliceLength = 0;
            }
            // Add the number of arithmetic slices ending at the current position to the total count.
            arithmeticSliceCount += currentSliceLength;
        }
        // Return the total count of arithmetic slices in the array.
        return arithmeticSliceCount;
    }
}