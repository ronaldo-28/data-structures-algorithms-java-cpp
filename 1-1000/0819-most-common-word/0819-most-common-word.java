import java.util.*;

class Solution {
    private static final boolean[] IS_LETTER = new boolean[123];
    private static final int HASH_MASK = 0x3F;  
    private static final int CASE_OFFSET = 'a' - 'A';

    static {
        for (int c = 'a'; c <= 'z'; c++) {
            IS_LETTER[c] = true;
            IS_LETTER[c - CASE_OFFSET] = true;
        }
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        boolean[] bannedBucket = new boolean[HASH_MASK + 1];
        int[] freq = new int[HASH_MASK + 1];

        
        for (String b : banned) {
            int h = 0;
            for (int i = 0; i < b.length(); i++) {
                char c = b.charAt(i);
                h = ((h << 2) ^ (c & 31)) & HASH_MASK; 
            }
            bannedBucket[h] = true;
        }

        int maxFreq = 0, maxStart = 0, maxEnd = 0;
        int n = paragraph.length();
        char[] chars = paragraph.toCharArray();
        int i = 0;

        while (i < n) {
           
            while (i < n && !IS_LETTER[chars[i]]) i++;
            if (i >= n) break;

            int start = i;
            int h = 0;

           
            while (i < n && IS_LETTER[chars[i]]) {
                char c = chars[i];
                if (c < 'a') c = (char)(c + CASE_OFFSET);
                h = ((h << 2) ^ (c & 31)) & HASH_MASK;
                chars[i] = c;
                i++;
            }

            if (!bannedBucket[h]) {
                int f = ++freq[h];
                if (f > maxFreq) {
                    maxFreq = f;
                    maxStart = start;
                    maxEnd = i;
                }
            }
        }

        
        return new String(chars, maxStart, maxEnd - maxStart);
    }

    
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.mostCommonWord(
            "Bob hit a ball, the hit BALL flew far after it was hit.",
            new String[]{"hit"})); // -> ball
    }
}