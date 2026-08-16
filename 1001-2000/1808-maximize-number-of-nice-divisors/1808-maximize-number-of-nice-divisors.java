class Solution {
    // Define the modulo constant for all operations.
    private final int MODULO = (int) 1e9 + 7;

    // Function to compute the maximum product of primeFactors with the largest sum.
    public int maxNiceDivisors(int primeFactors) {
        // If the total number of prime factors is less than 4, return the number itself.
        if (primeFactors < 4) {
            return primeFactors;
        }

        // If the total number of prime factors divided by 3 leaves no remainder,
        // return 3 raised to the power of primeFactors/3, modulo MODULO.
        if (primeFactors % 3 == 0) {
            return quickPower(3, primeFactors / 3);
        }

        // If the remainder is 1 when divided by 3, calculate power for primeFactors/3 - 1
        // and multiply the result by 4, then take modulo MODULO.
        if (primeFactors % 3 == 1) {
            return (int) (4L * quickPower(3, primeFactors / 3 - 1) % MODULO);
        }

        // If the remainder is 2, multiply 2 with 3 raised to the power of primeFactors/3,
        // then take modulo MODULO.
        return 2 * quickPower(3, primeFactors / 3) % MODULO;
    }

    // Helper function to perform quick exponentiation with modulo.
    private int quickPower(long base, long expo) {
        long result = 1;
        // Loop until the exponent becomes zero.
        while (expo > 0) {
            // If the current bit in the binary representation of the exponent is 1,
            // multiply result with base and take modulo.
            if ((expo & 1) == 1) {
                result = result * base % MODULO;
            }
            // Square the base and take modulo at each iteration.
            base = base * base % MODULO;
            // Right shift the exponent by 1 (equivalent to dividing by 2).
            expo >>= 1;
        }
        // Cast the result back to int before returning.
        return (int) result;
    }
}
