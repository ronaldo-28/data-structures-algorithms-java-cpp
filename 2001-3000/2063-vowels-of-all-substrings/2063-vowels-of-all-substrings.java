class Solution {
    public long countVowels(String word) {
        boolean[] vowel = new boolean[128];
        vowel['a'] = true;
        vowel['e'] = true;
        vowel['i'] = true;
        vowel['o'] = true;
        vowel['u'] = true;

        long ans = 0;
        int n = word.length();
        for(int i = 0; i < n; i++) {
            if(vowel[word.charAt(i)]) ans += (i + 1l) * (n - i);
        }
        return ans;
    }
}