class Solution {
    public int minAnagramLength(String s) {
        int n = s.length();
        int[] totalFreq = new int[26];
        for (int i = 0; i < n; i++) totalFreq[s.charAt(i) - 'a']++;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            if (canFormAnagram(s, k, n, totalFreq)) return k;
        }
        return n;
    }

    private boolean canFormAnagram(String s, int k, int n, int[] totalFreq) {
        int[] anagramCount = new int[26];
        for (int i = 0; i < k; ++i) anagramCount[s.charAt(i) - 'a']++;
        for (int c = 0; c < 26; c++) {
            if (anagramCount[c] * (n / k) != totalFreq[c]) return false;
        }
        int[] runningCount = new int[26];
        for (int i = 0; i < n; ++i) {
            runningCount[s.charAt(i) - 'a']++;
            if ((i + 1) % k == 0) {
                for (int c = 0; c < 26; c++) {
                    if (runningCount[c] != anagramCount[c] * ((i + 1) / k)) return false;
                }
            }
        }
        return true;
    }
}