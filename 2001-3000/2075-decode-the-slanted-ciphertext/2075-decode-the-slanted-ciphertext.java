class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        if (encodedText.length() == 0) {
            return "";
        }
        if (rows == 1) {
            return encodedText;
        }
        
        int m = rows;
        int n = encodedText.length()/m;
        // if ends at bottom right, the length is m*(n-m+1)
        // m*(n-m+1)+(m-1)
        int maxLen = m*(n-m+1)+(m-1);
        char[] text = new char[maxLen];
        int offset = 0;
        int idx = 0;
        OUTER:
        while (offset < n) {
            for (int r = 0; r < m; r++) {
                if (idx >= maxLen) {
                    break OUTER;
                }
                text[idx++] = encodedText.charAt(r*n+r+offset);
            }
            offset++;
        }

        idx--;
        while (idx >= 0 && text[idx] == ' ') {
            idx--;
        }
        char[] trimmed = new char[idx+1];
        for (int i = 0; i < idx+1; i++) {
            trimmed[i] = text[i];
        }
        return new String(trimmed);
    }
}