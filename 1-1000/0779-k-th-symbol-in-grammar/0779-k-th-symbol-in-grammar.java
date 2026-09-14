class Solution {
    // Method to find the kth symbol in the nth row of the grammar
    public int kthGrammar(int n, int k) {
        // Calculate the bit count of k-1
        // The bit pattern of k-1 reveals which value will be present at position k
        int bitCountOfKMinusOne = Integer.bitCount(k - 1);
      
        // If bit count is odd, the kth symbol is 1, otherwise it's 0
        // We use the bitwise AND operator with 1 to get the last bit 
        // which will be the answer, 1 for odd and 0 for even
        return bitCountOfKMinusOne & 1;
    }
}