class Solution {
    // Class instance variables to hold the input string and the minimum repeat-count 'k'.
    private String inputString;
    private int minRepeats;

    // Public method to initiate the longest substring search.
    public int longestSubstring(String s, int k) {
        this.inputString = s;
        this.minRepeats = k;
        // Start the depth-first search for the longest substring.
        return depthFirstSearch(0, s.length() - 1);
    }

    // A private helper method for the depth-first search to find the longest substring.
    private int depthFirstSearch(int left, int right) {
        // Array to count occurrences of each character.
        int[] charCounts = new int[26];
        for (int i = left; i <= right; ++i) {
            charCounts[inputString.charAt(i) - 'a']++;
        }

        // Initialize the variable ‘splitChar’ that holds the character to split on.
        char splitChar = 0;
        for (int i = 0; i < 26; ++i) {
            // Find the first character that occurs less than 'k' times, if any.
            if (charCounts[i] > 0 && charCounts[i] < minRepeats) {
                splitChar = (char) (i + 'a');
                break;
            }
        }
      
        // If no split character is found (all characters occur at least k times), return the substring length.
        if (splitChar == 0) {
            return right - left + 1;
        }

        // Initialize the start index for the next segment of the search.
        int start = left;
        int maximumLength = 0;
        while (start <= right) {
            // Skip all occurrences of the split character.
            while (start <= right && inputString.charAt(start) == splitChar) {
                start++;
            }
            if (start > right) { // If there is no non-split character left, break.
                break;
            }
          
            // Find the next segment without split character.
            int end = start;
            while (end <= right && inputString.charAt(end) != splitChar) {
                end++;
            }
          
            // Calculate the maximum length for the current segment.
            int segmentLength = depthFirstSearch(start, end - 1);
          
            // Update the maximum length if segment length is larger.
            maximumLength = Math.max(maximumLength, segmentLength);
          
            // Move to the next potential segment.
            start = end;
        }
      
        // Return the maximum length found.
        return maximumLength;
    }
}