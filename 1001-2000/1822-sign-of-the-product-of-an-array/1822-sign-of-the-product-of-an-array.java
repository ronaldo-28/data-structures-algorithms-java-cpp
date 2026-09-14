class Solution {
    /**
     * Returns the sign of the product of all elements in the array.
     * It returns:
     *  1 if the product is positive,
     * -1 if the product is negative,
     *  0 if the product is zero.
     *
     * Explanation:
     * Instead of multiplying all numbers (which can cause overflow),
     * we determine the sign by iterating through the array.
     * - If any number is zero, the product is zero.
     * - For each negative number, we flip the sign.
     *
     * @param nums the array of integers
     * @return the sign of the product of all values in nums
     */
    public int arraySign(int[] nums) {
        // Step 1 (Explanation): Initialize sign as 1 (positive).
        int sign = 1;
        
        // Step 2 (Explanation): Iterate over each number in the array.
        for (int num : nums) {
            // Step 3 (Explanation): If a number is 0, return 0 immediately.
            if (num == 0) {
                return 0;
            }
            // Step 4 (Explanation): If the number is negative, flip the sign.
            if (num < 0) {
                sign = -sign;
            }
        }
        
        // Step 5 (Explanation): Return the resulting sign after processing all numbers.
        return sign;
    }
}
