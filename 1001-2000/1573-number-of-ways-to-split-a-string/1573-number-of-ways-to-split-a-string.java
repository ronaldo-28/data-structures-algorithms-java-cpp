class Solution {
    public int numWays(String s) {
        
        // Count the number of '1's in the string, while also saving 
        // the index to each '1' into the array ones[].
        byte[] sc = new byte[s.length()];
        s.getBytes(0, s.length(), sc, 0);                        
        int[] ones = new int[Math.min(sc.length, 6000)];
        int onesCount = 0;
        for (int i = 0; i < sc.length; i++) {
            if (sc[i] == '1') {
                ones[onesCount] = i;
                onesCount++;
            }
        }

        // If number of '1's is not a multiple of 3, then the split 
        // can not be done.  A '1's count of zero is still a multiple 
        // of 3:  3 x 0 == 0.
        if (onesCount % 3 != 0)  return 0;
        
        // If no '1's (string is all '0's), then the number of ways 
        // to split is calculated from only the string length.
        // Number of split combinations is:  1 + 2 + ... + len-3 + len-2
        // which is the summation of integers from 1 to len-2.  
        // The summation formula gives: (len-2)*(len-1)/2
        if (onesCount == 0) {
            return (int)(((long)(s.length() - 2) * 
                          (long)(s.length() - 1) / 2L) % 1000000007L);
        }
        
        // Divide the string into three segments.  The number of possible 
        // ways to split it is based the number_of_'0's + 1 between the 
        // last '1' of a segment and the first '1' of the next segment.
        //
        // We have the array ones[] which contains the index of all '1's 
        // in the string, so we divide the array into thirds to find the 
        // index of the last '1' of a segment and the index of the first 
        // '1' of the next segment.  The number of split positions between 
        // segments is: start_'1'_index_next - end_'1'_index_prev + 1.  
        // For each possible split position between two segments, all split 
        // positions between the other segments are possible, so we multiply 
        // the number of possible split positions between all segments to 
        // get the result to return.
        int end1 = ones[onesCount / 3 - 1];
        int start2 = ones[onesCount / 3];
        int end2 = ones[onesCount * 2 / 3 - 1];
        int start3 = ones[onesCount * 2 / 3];
        return (int)((long)(start2 - end1) * 
                     (long)(start3 - end2) % 1000000007L);
    }
}