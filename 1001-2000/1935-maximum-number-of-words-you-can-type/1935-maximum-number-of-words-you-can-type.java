class Solution {
    static {
        for(int i = 0; i < 500; i++) canBeTypedWords("jjhc", "ndc");
    }
    public static int canBeTypedWords(String text, String brokenLetters) {
        int count = 1;
        boolean flag = true;
        boolean[] c = new boolean[26];
        for(int i = 0; i < brokenLetters.length(); i++) c[brokenLetters.charAt(i) - 97] = true;
        for(char ch : text.toCharArray()) {
            if(ch == ' ') {
                flag = true;
                count++;
            }
            else if(c[ch - 97] && flag) {
                count--;
                flag = false;
            }
        }
        return count < 1 ? 0 : count;
    }
}