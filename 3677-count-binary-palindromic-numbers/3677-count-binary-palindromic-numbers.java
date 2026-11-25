class Solution {
    public int countBinaryPalindromes(long n) {
        if (n == 0) return 1;
        if (n == 1) return 2;

        // for each bit length until the bit length of n (exclusive)
        long nn = 3;
        int ret = 2, k = 2;
        while (nn < n) {
            // increment the answer with the number of k bit palindromes
            ret += 1<<(((k + 1)>>1) - 1);
            nn = (nn<<1) + 1;
            k++;
        }
        
        // extract the left part from n
        int rlen = k>>1;
        nn = n>>rlen;

        // make the count base on the left part
        // e.g. 10110xxxx, the number of 9-bit palindromes we aleady have so far will be 110, i.e. 6
        ret += nn - (((long)1)<<(k - rlen - 1));
        
        // build the right part of the palindrome and count it if its smaller than or equals to n
        nn = nn<<rlen;
        for (int i = 0; i < rlen; i++) {
            if ((nn&(((long)1)<<(k - i - 1))) > 0) nn += ((long)1)<<i;
        }
        if (nn <= n) ret++;

        return ret;
    }
}