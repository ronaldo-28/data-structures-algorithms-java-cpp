class Solution {
    public String lastNonEmptyString(String s) {
        int[] freq = new int[26];
        StringBuilder result = new StringBuilder();
        int maxFreq = 0; 

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        for (int i = 0; i < freq.length; i++) {
             maxFreq = Math.max(maxFreq, freq[i]);
        }

        if (maxFreq == 1) {
            return s;
        }

        int totalLength = 0;

        for (int i = 0; i < freq.length; i++) {
            freq[i] -= Math.min(freq[i], maxFreq - 1);
            totalLength += freq[i];
        }

        for (int i = s.length() - 1; i >= 0 && totalLength > 0; i--) {
            char c = s.charAt(i);

            if (freq[c - 'a'] > 0) {
                result.append(c);
                freq[c - 'a']--;
                totalLength--;
            }
        }

        result.reverse();
        return result.toString();
    }
}