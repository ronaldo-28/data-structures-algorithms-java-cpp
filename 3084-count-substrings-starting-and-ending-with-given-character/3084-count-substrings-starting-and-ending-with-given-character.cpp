class Solution {
public:
    long long countSubstrings(string s, char c) {
        long long count = 0;
        for (char ch : s) {
            if (ch == c) {
                count++;
            }
        }

        // Consider all occurrences as potential starting points for substrings
        // and calculate combinations using the formula for unordered pairs
        return count * (count - 1) / 2 + count; // Add single-character substrings
    }
};