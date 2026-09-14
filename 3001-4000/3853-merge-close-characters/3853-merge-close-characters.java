import java.util.Arrays;

class Solution {
    public String mergeCharacters(String s, int k) {
        StringBuilder res = new StringBuilder();
        // Initialize frequency array with -1 to track the last index in 'res'
        int[] freq = new int[26];
        Arrays.fill(freq, -1);

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            int curr = ch - 'a';

            // Check if the character was recently added within the k-distance window
            if (freq[curr] != -1 && (res.length() - freq[curr]) <= k) {
                continue;
            }

            // Update the last seen index in the resulting builder
            freq[curr] = res.length();
            res.append(ch);
        }

        return res.toString();
    }
}