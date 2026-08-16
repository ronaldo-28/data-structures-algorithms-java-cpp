class Solution {
    // Define the modulus value as a constant for mod operations
    private static final int MOD = 1000000007;

    // This method calculates the count of good numbers
    public int countGoodNumbers(long n) {
        // Good numbers are created by even indices with 5 choices (0 to 4)
        // and odd indices with 4 choices (0, 2, 4, 6, 8).
        // We use exponentiation by squaring to compute the large powers efficiently
        // We need to use (n+1)/2 for the power of 5 if n is odd.
        // Similarly, we use n/2 for the power of 4, no matter if n is even or odd.
        return (int) (myPow(5, (n + 1) / 2) * myPow(4, n / 2) % MOD);
    }

    // Helper method for fast exponentiation
    private long myPow(long base, long exponent) {
        long result = 1; // Start from the identity value
        while (exponent != 0) {
            if ((exponent & 1) == 1) {
                // If the current exponent bit is 1, multiply the result by base
                result = (result * base) % MOD;
            }
            // Square base and move to the next bit of the exponent
            base = (base * base) % MOD;
            exponent >>= 1; // Right shift exponent by 1 (equivalent to dividing by 2)
        }
        return result;
    }
}