class Solution {
    public int integerReplacement(int n) {
        int steps = 0; // Counter for the number of steps taken to transform 'n' to 1
      
        while (n != 1) {
            if ((n & 1) == 0) { // If 'n' is even
                n >>>= 1; // Right shift (unsigned) to divide 'n' by 2
            } else if (n != 3 && (n & 3) == 3) { // If 'n' is not 3 and the last two bits are 11
                n++; // Increment 'n' since it leads to more 0s when it's divided by 2 subsequently 
            } else {
                n--; // Decrement 'n' if it's odd and doesn't match the previous case
            }
            steps++; // Increment the step count after each operation
        }
        return steps; // Return the total number of steps once 'n' is reduced to 1
    }
}