class Solution {
    // Method to calculate the Hamming Distance between two integers.
    public int hammingDistance(int x, int y) {
        // Use XOR to find differing bits between x and y
        int xorResult = x ^ y;
      
        // Integer.bitCount method counts the number of one-bits in the XOR result.
        // This count represents the number of differing bits, which is the Hamming Distance.
        int count = Integer.bitCount(xorResult);
      
        // Return the count of differing bits as the Hamming Distance
        return count;
    }
}