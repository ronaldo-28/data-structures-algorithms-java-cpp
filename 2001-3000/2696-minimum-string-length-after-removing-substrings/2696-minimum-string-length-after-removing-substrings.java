class Solution {
    public int minLength(String s) {
        int ptr = -1;
        char[] str = new char[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (ptr == -1) str[++ptr] = s.charAt(i);
            else {
                if ((str[ptr] == 'A' && s.charAt(i) == 'B') || (str[ptr] == 'C' && s.charAt(i) == 'D')) {
                    ptr--;
                } else {
                    str[++ptr] = s.charAt(i);
                }
            }
        }

        return ptr + 1;
    }
}