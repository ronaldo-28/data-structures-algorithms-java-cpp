class Solution {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int top = 0;
        int n = s.length();
        char[] stack = new char[k];
        int count = 0;
        for(char c : s.toCharArray()) {
            if(c == letter) count++;
        }
        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while(top > 0 && stack[top - 1] > c && top + n - i > k && (count > repetition || stack[top - 1] != letter)) {
                if(stack[--top] == letter) repetition++;
            }
            if(top != k) {
                if(c == letter) {
                    stack[top++] = letter;
                    repetition--;
                }else if(k - top > repetition) stack[top++] = c;
            }
            if(c == letter) count--;
        }
        return String.valueOf(stack);
    }
}