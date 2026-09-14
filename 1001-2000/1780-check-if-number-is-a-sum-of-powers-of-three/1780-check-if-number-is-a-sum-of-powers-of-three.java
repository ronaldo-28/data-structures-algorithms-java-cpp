class Solution {
    public boolean checkPowersOfThree(int n) {
        // Loop until n is greater than 0
        while (n > 0) {
            // If the remainder of n divided by 3 is greater than 1,
            // it means n is not a sum of powers of three (since it either
            // has a factor of 3's power greater than 1,
            // or it includes a number that's not a power of 3).
            // So the function returns false.
            if (n % 3 > 1) {
                return false;
            }
            // Divide n by 3 to reduce the problem to a smaller instance
            // of the same problem, checking the next power of 3.
            n /= 3;
        }
        // After the loop, if n has been reduced to 0, it means n can fully be
        // represented as a sum of powers of three.
        // The function returns true in this case.
        return true;
    }
}