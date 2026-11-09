class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();
        int[] count = new int[26];

        for (int i = 0; i < n / 2; i++)
            count[s.charAt(i) - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) 
            if (count[i] > 0){
                sb.append(String.valueOf((char)(i + 'a')).repeat(count[i]));
            }

        if (n % 2 == 1)
            sb.append(s.charAt(n / 2));

        for (int i = 25; i >= 0; i--){
            if (count[i] > 0){
                sb.append(String.valueOf((char)(i + 'a')).repeat(count[i]));
            }
        }
        return sb.toString();
    }
}