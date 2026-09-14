class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        // j = 19, i = 5, jump = 5
        // c = 'n'
        // word = internationalization length = 20
        // abbr = i5a11o1, length = 7
        int j = 0;
        int jump = 0;
        for(int i = 0; i < abbr.length(); i++) {
            char c = abbr.charAt(i);
            if (c > '0' && c <= '9' || c == '0' && jump > 0) {
                jump = jump * 10 + c - '0';
                continue;
            }
            j += jump;
            jump = 0;
            if (j >= word.length() || word.charAt(j) != c || c == '0') {
                return false;
            }
            j++;
        }
        if (jump > 0) {
            j += jump;
        }
        return j == word.length();
    }
}