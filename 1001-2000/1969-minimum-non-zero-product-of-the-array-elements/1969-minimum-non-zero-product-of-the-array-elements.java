class Solution {

    // This method calculates the minimum non-zero product of the elements of the
    // array created by 'p' given features.
    public int minNonZeroProduct(int p) {
        final int MOD = (int) 1e9 + 7; // Define the modulo as per the problem statement.
      
        // Calculate the base value 'a' - it's 2^p - 1 modulo MOD.
        long baseValueA = ((1L << p) - 1) % MOD;

        // Calculate the power value 'b' - it requires using a helper method which
        // computes (2^p - 2) raised to the power of (2^(p-1)-1) modulo MOD.
        long powerValueB = qpow(((1L << p) - 2) % MOD, (1L << (p - 1)) - 1, MOD);
      
        // Return the minimum product modulo MOD.
        return (int) (baseValueA * powerValueB % MOD);
    }

    // This helper method calculates a^b modulo 'mod' using the fast exponentiation method.
    private long qpow(long base, long exponent, int mod) {
        long result = 1;
        while (exponent > 0) {
            // If the current bit is set, multiply the result by the current base modulo 'mod'.
            if ((exponent & 1) == 1) {
                result = (result * base) % mod;
            }

            // Square the base for the next iteration and take modulo 'mod'.
            base = (base * base) % mod;
          
            // Right shift exponent by 1 (divide by 2) for the next iteration.
            exponent >>= 1;
        }
        return result;
    }
}