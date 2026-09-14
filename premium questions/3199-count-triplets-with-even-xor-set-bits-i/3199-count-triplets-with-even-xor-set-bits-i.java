class Solution {
    public int tripletCount(int[] a, int[] b, int[] c) {
        // Preprocess arrays to count even and odd set bits
        int[] aCount = preprocess(a);
        int[] bCount = preprocess(b);
        int[] cCount = preprocess(c);

        // Calculate valid triplets
        int evenTriplets = aCount[0] * bCount[0] * cCount[0]; // even + even + even
        int oddOddEvenTriplets = aCount[1] * bCount[1] * cCount[0]; // odd + odd + even
        int oddEvenOddTriplets = aCount[1] * bCount[0] * cCount[1]; // odd + even + odd
        int evenOddOddTriplets = aCount[0] * bCount[1] * cCount[1]; // even + odd + odd

        // Total count of valid triplets
        return evenTriplets + oddOddEvenTriplets + oddEvenOddTriplets + evenOddOddTriplets;
    }

    // Method to preprocess array and count even and odd set bits
    private int[] preprocess(int[] arr) {
        int evenCount = 0;
        int oddCount = 0;
        // Iterate through array elements
        for (int num : arr) {
            // Count set bits and determine if even or odd
            if (Integer.bitCount(num) % 2 == 0) { // Check if number of set bits is even
                evenCount += 1; // Increment even count
            } else {
                oddCount += 1; // Increment odd count
            }
        }
        // Return counts of even and odd set bits
        return new int[]{evenCount, oddCount};
    }
}    