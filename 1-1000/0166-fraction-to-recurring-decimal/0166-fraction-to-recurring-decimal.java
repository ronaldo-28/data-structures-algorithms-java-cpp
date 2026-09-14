
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        // Step 1: Convert numerator and denominator to long to avoid overflow
        long lnum = numerator, lden = denominator;

        // Step 2: Handle the zero case
        if (lnum == 0) {
            return "0";
        }

        // Step 3: If the result divides evenly (no decimal part), return the integer part
        if (lnum % lden == 0) {
            return Long.toString(lnum / lden);
        }

        // Step 4: Create a StringBuilder to construct the result
        StringBuilder number = new StringBuilder();

        // Step 5: Handle the negative sign
        // XOR operator: if only one is negative, then result is negative
        if ((lden < 0) ^ (lnum < 0)) {
            number.append('-');
        }

        // Step 6: Convert both numbers to positive
        lnum = Math.abs(lnum);
        lden = Math.abs(lden);

        // Step 7: Append the integer part of the division
        number.append(lnum / lden);
        number.append('.');  // Add the decimal point

        // Step 8: Reduce the fraction by their GCD to simplify
        long GCD = gcd(lnum, lden);
        lnum /= GCD;
        lden /= GCD;

        // Step 9: Begin processing the fractional part
        lnum %= lden;   // Get initial remainder
        lnum *= 10;     // Start long division

        // Step 10: Check if the denominator has only 2s and 5s as prime factors
        // If so, it is a terminating decimal
        int twos = 0, fives = 0;
        long temp = lden;

        // Count how many times denominator is divisible by 2
        while (temp % 2 == 0) {
            twos++;
            temp /= 2;
        }

        // Count how many times denominator is divisible by 5
        while (temp % 5 == 0) {
            fives++;
            temp /= 5;
        }

        // Step 11: If only 2s and 5s, it's a terminating decimal
        if (temp == 1) {
            // Keep dividing and appending until there's no remainder
            while (lnum != 0) {
                number.append(lnum / lden);
                lnum = (lnum % lden) * 10;
            }
        } else {
            // Step 12: If not terminating, we might have a non-repeating prefix
            int nonRepLen = Math.max(twos, fives);

            for (int i = 0; i < nonRepLen; i++) {
                number.append(lnum / lden);
                lnum = (lnum % lden) * 10;
            }

            // Step 13: Detect repeating cycle by tracking remainders
            long initRem = lnum;  // Save initial remainder
            StringBuilder repeating = new StringBuilder();

            do {
                repeating.append(lnum / lden);
                lnum = (lnum % lden) * 10;
            } while (lnum != initRem);  // Stop when remainder repeats

            // Step 14: Wrap repeating part in parentheses
            number.append('(').append(repeating.toString()).append(')');
        }

        // Step 15: Return final string
        return number.toString();
    }

    // Helper method to compute GCD (Greatest Common Divisor)
    private long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}