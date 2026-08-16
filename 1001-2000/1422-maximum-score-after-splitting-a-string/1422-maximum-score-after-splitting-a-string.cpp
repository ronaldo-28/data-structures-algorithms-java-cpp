class Solution {
public:
    int maxScore(string s) {
        int n = s.length();
        int maxScore = 0;
        int zerosLeft = 0; // Count of zeros in the left substring
        int onesRight = 0; // Count of ones in the right substring

        // Count the total number of ones in the entire string initially
        for (char c : s) {
            if (c == '1') {
                onesRight++;
            }
        }

        // Iterate through the string, considering each position as a potential split point
        for (int i = 0; i < n - 1; i++) {
            if (s[i] == '0') {
                zerosLeft++;
            } else {
                onesRight--; // If it's a 1, it was in the right, but now it's not
            }
            maxScore = max(maxScore, zerosLeft + onesRight);
        }

        return maxScore;
    }
};