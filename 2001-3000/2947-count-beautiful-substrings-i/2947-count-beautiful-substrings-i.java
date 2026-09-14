class Solution {
    public int beautifulSubstrings(String s, int k) {
        int n = s.length();
        boolean[] isVowel = new boolean[26];
        for (char c : new char[]{'a','e','i','o','u'}) isVowel[c - 'a'] = true;

        int step = 1;
        while (step <= k) {
            if ((step * step) % k == 0) break;
            step++;
        }

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (isVowel[s.charAt(i) - 'a'] ? 1 : 0);
        }

        int ans = 0;
        for (int len = step; len <= n; len += step) {
            for (int i = 0; i + len <= n; i++) {
                int vowels = prefix[i + len] - prefix[i];
                int consonants = len - vowels;
                if (vowels == consonants && (vowels * consonants) % k == 0) {
                    ans++;
                }
            }
        }
        return ans;
    }
}