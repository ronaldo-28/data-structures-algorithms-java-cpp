class Solution {
    public int evenNumberBitwiseORs(int[] nums) {
        int n = nums.length; // Get the length of the array
        int res = 0; // Initialize result to 0

        for (int i = 0; i < n; i++) { // Iterate through the array
            if (nums[i] % 2 == 0) { // Check if the number is even
                res = res | nums[i]; // Apply bitwise OR operation
            }
        }

        return res; // Return the final result
    }
}