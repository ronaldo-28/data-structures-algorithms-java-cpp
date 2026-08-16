class Solution {
    int[][] dp;

    public boolean isMatch(String s, String p) {
        dp = new int[s.length()][p.length()];

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                dp[i][j] = -1;
            }
        }

        return rec(0, 0, s, p);
    }

    private boolean rec(int i, int j, String s, String p) {
        // Base case: If we've processed the entire string and pattern
        if (i >= s.length() && j >= p.length()) 
            return true;

        // If the string is exhausted but the pattern isn't
        if (i >= s.length()) {
            // The remaining pattern must consist of pairs of "character + '*'"
            if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                return rec(i, j + 2, s, p);  // Skip "char*"
            }
            return false;  // Otherwise, the pattern can't match the empty string
        }
        
        //If the pattern is exhausted but the string isn't
        if (j >= p.length()) 
            return false;

        if (dp[i][j] != -1)
            return dp[i][j] == 0 ? false : true;

        // Check if current characters match or if there's a '.' in the pattern
        boolean currentCharMatch = (i < s.length() && j < p.length() && 
                                    (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.'));

        boolean ans = false;

        // If the next character in the pattern is a '*', we can either:
        // 1. Skip the '*' by moving the pattern pointer ahead by 2.
        // 2. Use the '*' to match one or more characters by moving the string pointer ahead by 1.
        boolean starNext = (j + 1 < p.length() && p.charAt(j + 1) == '*');

        // If there's a '*' in the pattern, we handle both cases
        if (starNext) {
            ans |= rec(i, j + 2, s, p);  // Skip '*' in the pattern
            ans |= currentCharMatch && rec(i + 1, j, s, p); // Use '*' to match one or more characters
        } else {
            // No '*' and current characters match
            ans |= currentCharMatch && rec(i + 1, j + 1, s, p); // Match the current character and move both pointers
        }

        // Store the result in the dp table for the current state
        dp[i][j] = ans ? 1 : 0;
        
        return ans;
    }
}