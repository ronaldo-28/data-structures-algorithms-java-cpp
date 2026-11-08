class Solution {
        public long countValidSubstrings(String s) {
        int a = -1, b = -1, c = -1;
        long res = 0;
        for (int i = 0; i < s.length(); ++i) {
            char v = s.charAt(i);
            if (v == 'a') {
                a = i;
            } else if (v == 'b') {
                b = a;
            } else {
                c = b;
            }
            res += i - c;
        }
        return res;
    }
}