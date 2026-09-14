class Solution {

    // Function to calculate the minimum number of operations required
    // to transform the integer 'n' into zero by flipping exactly one bit in a binary representation
    public int minimumOneBitOperations(int n) {
        int result = 0; // Initialize the result variable to store the minimum operations

        // Iterate until n becomes zero
        while (n > 0) {
            // Use XOR to keep track of bit operations, exploiting the 
            // property that going down to zero with one-bit operations can be seen as toggling bits
            result ^= n;

            // Right shift 'n' by 1 to divide it by 2, moving to the next bit
            n >>= 1;
        }
      
        // Return the calculated result
        return result;
    }
}