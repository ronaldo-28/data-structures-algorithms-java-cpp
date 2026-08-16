class Solution {

    /**
     * Returns the length of the longest subsequence with a decimal value less than or equal to k.
     *
     * @param s The binary string.
     * @param k The upper bound for decimal value of the subsequence.
     * @return The length of the longest subsequence.
     */
    public int longestSubsequence(String s, int k) {
        int longestLength = 0; // The length of the longest valid subsequence
        int decimalValue = 0;  // The decimal value of the considered subsequence

        // Iterate over the string in reverse because the least significant bits
        // can be considered in isolation for the smallest possible addition to the value.
        for (int index = s.length() - 1; index >= 0; --index) {
            // If we find a '0', it doesn't add to the value,
            // so we can always include it in the subsequence
            if (s.charAt(index) == '0') {
                ++longestLength;
            }
            // Only consider '1's if the length of the sequence is less than 30
            // and adding the '1' wouldn't exceed k. We check length < 30
            // because 2^30 exceeds Integer.MAX_VALUE and cannot be represented by int.
            else if (longestLength < 30 && (decimalValue | (1 << longestLength)) <= k) {
                // '|' is the bitwise OR operator. Here we add the value represented by
                // a '1' at the current position to the decimalValue (if it does not exceed k).
                decimalValue |= 1 << longestLength;
                // Increment the length because we've added a '1' to the subsequence.
                ++longestLength;
            }
        }
        return longestLength; // Return the computed length of the longest subsequence
    }
}