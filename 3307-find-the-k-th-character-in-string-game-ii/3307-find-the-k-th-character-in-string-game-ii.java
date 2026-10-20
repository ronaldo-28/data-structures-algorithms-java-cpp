class Solution {
    public char kthCharacter(long k, int[] operations) {
        // Initialize n to 1. It keeps track of the length of the current sequence.
        long n = 1;
        // i keeps track of the number of operations performed to reach the current n.
        int i = 0;

        // Loop to find the smallest n that is greater than or equal to k.
        while (n < k) {
            n *= 2; // Double n to simulate the sequence expansion.
            i++; // Increment i to match the number of doublings.
        }

        // Initialize d to 0. It represents the total adjustment from operations.
        int d = 0;

        // While n is greater than 1, adjust k and d according to the sequence's behavior.
        while (n > 1) {
            // Check if k falls in the second half of the current sequence.
            if (k > n / 2) {
                k -= n / 2; // Adjust k to map onto the second half.
                d += operations[i - 1]; // Apply the corresponding operation adjustment.
            }
            n /= 2; // Halve n to simulate reducing the sequence to a prior state.
            i--; // Decrement i to backtrack in operations array.
        }

        // Compute the character by adjusting 'a' with the accumulated operation modifications.
        return (char) ('a' + (d % 26));
    }
}