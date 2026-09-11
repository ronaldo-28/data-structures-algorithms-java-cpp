class Solution {
    public int longestCommonPrefix(String s, String t) {
        int n = s.length();
        int m = t.length();

        int i = 0;

        while (i < n && i < m && s.charAt(i) == t.charAt(i)) {
            i++;
        }

        // No mismatch
        if (i == n || i == m) {
            return i;
        }

        int a = i + 1; // skip s[i]
        int b = i;

        while (a < n && b < m && s.charAt(a) == t.charAt(b)) {
            a++;
            b++;
        }

        return b;
    }
}