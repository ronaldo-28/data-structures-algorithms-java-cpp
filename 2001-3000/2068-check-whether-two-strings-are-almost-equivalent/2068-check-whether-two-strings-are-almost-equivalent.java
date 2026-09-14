class Solution {
    public boolean checkAlmostEquivalent(String word1, String word2) {
        
        int[] freq = new int[26];

        // Count characters of word1
        for (char ch : word1.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Subtract characters of word2
        for (char ch : word2.toCharArray()) {
            freq[ch - 'a']--;
        }

        // Check difference
        for (int i = 0; i < 26; i++) {
            if (Math.abs(freq[i]) > 3) {
                return false;
            }
        }

        return true;
    }
}