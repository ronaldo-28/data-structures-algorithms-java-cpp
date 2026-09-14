import java.util.Arrays;

class Solution {
    private static final int MOD = (int) 1e9 + 7;

    public int countAnagrams(String s) {
        char[] charArray = s.toCharArray();
        long ans = 1L;
        long mul = 1L;
        
        // Fast fixed-size lookup cache
        int[] cnt = new int[26];
        int j = 0; // Tracks the current length of the active word

        for (char c : charArray) {
            if (c == ' ') {
                // Clear the cache for the next word using fast memory fill
                Arrays.fill(cnt, 0);
                j = 0;
            } else {
                // Increment position length and track permutation multiplier
                ans = (ans * ++j) % MOD;
                // Increment character frequency and track duplicate divider
                mul = (mul * ++cnt[c - 'a']) % MOD;
            }
        }

        // Modular Inverse division: ans / mul => ans * (mul^(MOD-2))
        return (int) (ans * pow(mul, MOD - 2) % MOD);
    }

    // High performance fast-exponentiation loop
    private long pow(long x, int n) {
        long res = 1L;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            n >>= 1; // Faster than division
        }
        return res;
    }
}