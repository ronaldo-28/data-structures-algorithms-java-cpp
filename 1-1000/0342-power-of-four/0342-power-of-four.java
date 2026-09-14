class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }

        // Check if it's a power of 2 and has an even number of trailing zeros
        return (n & (n - 1)) == 0 && (n & 0x55555555) != 0; 
    }
}