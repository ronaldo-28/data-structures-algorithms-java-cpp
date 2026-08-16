class Solution {
  
    // This method calculates the number of ways a monkey can move, given `n` movements.
    public int monkeyMove(int n) {
        // Defining the modulo value as 1e9 + 7 to keep the result within integer limits
        final int MOD = (int) 1e9 + 7;

        // Use the quick power algorithm to calculate 2 raised to the power of `n`, reduce the result by 2, and ensure it's within the modulo value.
        return (quickPower(2, n, MOD) - 2 + MOD) % MOD;
    }

    // This helper method efficiently calculates (a^b) mod `mod` using the quick power algorithm.
    private int quickPower(long base, int exponent, int mod) {
        // Initialize the result to 1 (identity for multiplication).
        long result = 1;
        // Iterate as long as the exponent is greater than 0.
        while (exponent > 0) {
            // If the current bit of exponent is '1', multiply the result by the current base and take modulo
            if ((exponent & 1) == 1) {
                result = (result * base) % mod;
            }
            // Square the base and take modulo for the next bit.
            base = (base * base) % mod;
            // Right shift the exponent to check the next bit.
            exponent >>= 1;
        }
        // Casting the long result back to integer before returning.
        return (int) result;
    }
}