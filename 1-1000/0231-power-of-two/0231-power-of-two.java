class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        // Use bitwise AND to check if only one bit is set
        return (n & (n - 1)) == 0;
    }
}