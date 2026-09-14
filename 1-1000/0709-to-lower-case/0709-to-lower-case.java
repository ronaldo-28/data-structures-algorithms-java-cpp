class Solution {
    public String toLowerCase(String s) {
        // Step 1: Initialize a StringBuilder to build the resulting string.
        // Explanation: StringBuilder is efficient for constructing strings character by character.
        StringBuilder sb = new StringBuilder();
        
        // Step 2: Iterate through each character in the input string.
        for (char c : s.toCharArray()) {
            // Step 3: Check if the character is an uppercase letter.
            // Explanation: Uppercase letters in ASCII range from 'A' to 'Z'.
            if (c >= 'A' && c <= 'Z') {
                // Convert the uppercase letter to lowercase by adding the offset (32).
                sb.append((char) (c + 32));
            } else {
                // If it's not an uppercase letter, append it as is.
                sb.append(c);
            }
        }
        
        // Step 4: Return the resulting string.
        // Explanation: The StringBuilder now contains the complete lowercase string.
        return sb.toString();
    }
}
