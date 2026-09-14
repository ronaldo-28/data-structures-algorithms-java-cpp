class Solution {
    String s;
    int n;
    int ans = 0;

    public void count(int l, int r) {
        int left = l;
        int uniVowel = 0;
        int freq[] = new int[26];
        for (int right = l; right < r; right++) {
            int c = s.charAt(right);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                if (freq[c - 'a'] == 0) {
                    uniVowel++;
                }
                freq[c - 'a']++;
            }
            while (uniVowel == 5) {
                ans += (r - right);
                char leftChar = s.charAt(left);
                if (leftChar == 'a' || leftChar == 'e' || leftChar == 'i' || leftChar == 'o' || leftChar == 'u') {
                    freq[leftChar - 'a']--;
                    if (freq[leftChar - 'a'] == 0) {
                        uniVowel--;
                    }
                }
                left++;
            }
        }
    }

    public int countVowelSubstrings(String word) {
        s = word;
        n = word.length();
        int left = 0;
        for (int right = 0; right < n; right++) {
            int c = word.charAt(right);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                continue;
            } else {
                count(left, right);
                left = right + 1;
            }
        }
        count(left, n);
        return ans;
    }
}