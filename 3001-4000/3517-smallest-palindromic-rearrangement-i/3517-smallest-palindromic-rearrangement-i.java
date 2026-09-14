class Solution {
    public String smallestPalindrome(String s) {
        int[] c = new int[26];
        int n = s.length();
        String middle = (n % 2 == 0) ? "" : String.valueOf(s.charAt(n >> 1));
        StringBuilder ans = new StringBuilder();
        n >>= 1;
        for(int i = 0; i < n; i++){
            c[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++){
            ans.append(String.valueOf((char)(i + 'a')).repeat(c[i]));
        }

        return ans.toString() + middle + ans.reverse().toString();
    }
}