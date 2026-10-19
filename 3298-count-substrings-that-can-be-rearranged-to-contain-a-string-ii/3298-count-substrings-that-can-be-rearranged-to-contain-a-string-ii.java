class Solution {
    public long validSubstringCount(String word1, String word2) {
        if (word2.length() > word1.length()) return 0;

        char[] w1s = word1.toCharArray();
        char[] w2s = word2.toCharArray();
        int w1len = w1s.length, w2len = w2s.length;

        long ret = 0;
        int[] v2 = new int[26];
        for (char c : w2s) v2[c - 'a']++;

        int l = 0, missing = w2len;
        for (int r = 0; r < w1len; r++) {
            int right = w1s[r] - 'a';
            if (v2[right] > 0) missing--;
            v2[right]--;

            while (missing == 0) {
                ret += w1len - r;

                int left = w1s[l] - 'a';
                v2[left]++;
                if (v2[left] > 0) missing++;
                l++;
            }
        }
        return ret;
    }
}