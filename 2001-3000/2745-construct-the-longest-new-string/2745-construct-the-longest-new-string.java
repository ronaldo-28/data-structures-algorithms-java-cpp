class Solution {
    // Method to calculate the longest string length
    public int longestString(int x, int y, int z) {
        // If x is less than y
        if (x < y) {
            // Multiply x by 2, add z and 1 to it, and then multiply the whole by 2
            return (x * 2 + z + 1) * 2;
        }
        // If x is greater than y
        if (x > y) {
            // Multiply y by 2, add z and 1 to it, and then multiply the whole by 2
            return (y * 2 + z + 1) * 2;
        }
        // If x is equal to y
        // Add x, y, and z together and then multiply the whole by 2
        return (x + y + z) * 2;
    }
}