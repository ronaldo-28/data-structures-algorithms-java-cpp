class Solution {
    public String addBoldTag(String s, String[] words) {
        int n = s.length();
        boolean[] isBold = new boolean[n];
        
        // 1. Mark all characters that belong to a matching word
        for (String word : words) {
            int len = word.length();
            int index = s.indexOf(word);
            
            // Find every occurrence of this word in the string
            while (index != -1) {
                for (int i = index; i < index + len; i++) {
                    isBold[i] = true;
                }
                // Look for the next occurrence starting 1 character over
                index = s.indexOf(word, index + 1);
            }
        }
        
        // 2. Build the final string using the boolean flags
        StringBuilder result = new StringBuilder();
        int i = 0;
        
        while (i < n) {
            if (isBold[i]) {
                // We found a bold section, open the tag
                result.append("<b>");
                
                // Keep moving forward as long as characters are marked bold
                while (i < n && isBold[i]) {
                    result.append(s.charAt(i));
                    i++;
                }
                
                // Close the tag when the bold section ends
                result.append("</b>");
            } else {
                // Standard character, just append it
                result.append(s.charAt(i));
                i++;
            }
        }
        
        return result.toString();
    }
}