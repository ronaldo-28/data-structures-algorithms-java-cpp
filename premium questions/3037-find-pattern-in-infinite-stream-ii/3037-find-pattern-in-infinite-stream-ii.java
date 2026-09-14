/**
 * Definition for an infinite stream.
 * class InfiniteStream {
 *     public InfiniteStream(int[] bits);
 *     public int next();
 * }
 */
// Use a hash of the pattern and a hash of a sliding window.
class Solution {
    public int findPattern(InfiniteStream infiniteStream, int[] pattern) {
        final int n = pattern.length;
        final long MOD = 1_000_000_000_007L;

        // Calculate the k for the hash calculations, which is 
        // k = (1 << n) % MOD
        // But this number can be too big to be calculated 
        // directly since n can be as large as 10_000, so 
        // it has to be calculated in pieces.
        final int K_BIT_INC = 20;
        int kRemaining = n;
        long k = 1;
        while (kRemaining > 0) {
            k = (k * (1L << Math.min(kRemaining, K_BIT_INC))) % MOD;
            kRemaining -= K_BIT_INC;
        }
        
        // Find the largest power of 2 greater than n.
        int nBitSize = 1;
        while (nBitSize <= n)  nBitSize <<= 1;
        final int N_BIT_MASK = nBitSize - 1;

        // Convert the pattern to a single long hash value.
        long patHash = 0;
        for (int pat : pattern) 
            patHash = ((patHash << 1) + pat) % MOD;
        
        // Fill the inintial window with bits from the stream to 
        // get the initial window's hash value.  Save all the 
        // stream values in a buffer.
        byte[] winBuff = new byte[nBitSize];
        int winBuffInnIdx = 0;
        int winBuffOutIdx = 0;
        long winHash = 0;
        int streamIdx;
        for (streamIdx = 0; streamIdx < n; streamIdx++) {
            int num = infiniteStream.next();
            winBuff[winBuffInnIdx++] = (byte)num;
            winHash = ((winHash << 1) + num) % MOD;
        }

        // Read bits from the stream into the sliding window to 
        // get rolling hash values.  Compare the sliding window 
        // hash value with the pattern hash value, looking for a 
        // match.  Save the stream values used in the sliding 
        // window, into a circular buffer.
        while (true) {
            if (patHash == winHash)  return streamIdx - n;
            int num = infiniteStream.next();
            winBuff[winBuffInnIdx++] = (byte)num;
            winBuffInnIdx &= N_BIT_MASK;
            winHash = ((winHash << 1) + num - winBuff[winBuffOutIdx++] * k + MOD) % MOD;
            winBuffOutIdx &= N_BIT_MASK;
            streamIdx++;
        }
    }
}