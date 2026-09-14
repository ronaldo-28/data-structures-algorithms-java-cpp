class Solution {
    public int strongPasswordChecker(String password) {
        int n = password.length();
        
        // Step 1: Count missing types: lowercase, uppercase, digit.
        int missingTypes = 3;
        boolean hasLower = false, hasUpper = false, hasDigit = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
        }
        if (hasLower) missingTypes--;
        if (hasUpper) missingTypes--;
        if (hasDigit) missingTypes--;
        
        // Step 2: Count repeating sequences and their replacement requirements.
        int replacements = 0; // total replacements needed for sequences.
        int[] repeats = new int[n]; // stores lengths of repeats mod 3 for sequences.
        int index = 0;
        for (int i = 0; i < n;) {
            int j = i;
            while (j < n && password.charAt(j) == password.charAt(i)) j++;
            int len = j - i;
            if (len >= 3) {
                repeats[index++] = len;
                replacements += len / 3;
            }
            i = j;
        }
        
        // Case 1: Password length is less than 6.
        if (n < 6) {
            return Math.max(missingTypes, 6 - n);
        }
        
        // Case 2: Password length is between 6 and 20 (inclusive).
        if (n <= 20) {
            return Math.max(missingTypes, replacements);
        }
        
        // Case 3: Password length is greater than 20.
        int deletions = n - 20;
        int remainingDeletions = deletions;
        
        // Optimize deletions on sequences with repeating characters.
        // First, try to reduce replacements by deleting in sequences where len % 3 == 0.
        for (int k = 0; k < index && remainingDeletions > 0; k++) {
            if (repeats[k] < 3) continue;
            if (repeats[k] % 3 == 0) {
                // Delete one character to reduce one replacement.
                repeats[k]--;
                remainingDeletions--;
                replacements--;  // one less replacement needed.
            }
        }
        
        // Next, delete in sequences where len % 3 == 1.
        for (int k = 0; k < index && remainingDeletions > 0; k++) {
            if (repeats[k] < 3) continue;
            if (repeats[k] % 3 == 1) {
                // Delete two characters to reduce one replacement.
                int deleteNeeded = Math.min(remainingDeletions, 2);
                // For each two deletions, one replacement can be reduced.
                if (deleteNeeded == 2) {
                    repeats[k] -= 2;
                    remainingDeletions -= 2;
                    replacements--;
                } else {
                    // If only one deletion is available, just apply it.
                    repeats[k]--;
                    remainingDeletions--;
                }
            }
        }
        
        // Finally, apply remaining deletions to any sequence.
        for (int k = 0; k < index && remainingDeletions > 0; k++) {
            if (repeats[k] < 3) continue;
            // How many deletions can we apply here?
            int needed = repeats[k] - 2; // after deletions, minimal length to not require replacement is 2.
            int deletionsApplied = Math.min(needed, remainingDeletions);
            repeats[k] -= deletionsApplied;
            remainingDeletions -= deletionsApplied;
            // Update replacements: each 3 deletions reduce one replacement.
            replacements -= deletionsApplied / 3;
        }
        
        // Return total steps: deletions + max(missingTypes, replacements)
        return deletions + Math.max(missingTypes, replacements);
    }
}
