class Solution {
    public int reverse(int x) {
        long rev = 0;                 // use long to detect overflow
        while (x != 0) {
            rev = rev * 10 + x % 10;  // pull off last digit and tack on
            x /= 10;
            // if we’ve exceeded the 32-bit signed int range, bail
            if (rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) rev;
    }
}
