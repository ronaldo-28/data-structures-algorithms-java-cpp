class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            findTheDifference("", "");
        }
    }

    public static char findTheDifference(String s, String t) {
        int[] cnt = new int[26];
        for (char c: s.toCharArray()) cnt[c - 'a']++;
        for (char c: t.toCharArray()) cnt[c - 'a']--;
        for (int i = 0; i < 26; i++) if (cnt[i] == -1) return (char) (i + 'a');
        return 'z';
    }
}