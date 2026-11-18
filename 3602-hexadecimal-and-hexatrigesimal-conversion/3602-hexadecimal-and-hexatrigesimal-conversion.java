class Solution {
    public String concatHex36(int n) {
    final char[] DIGITS = {
                '0','1','2','3','4','5','6','7','8','9',
                'A','B','C','D','E','F','G','H','I','J',
                'K','L','M','N','O','P','Q','R','S','T',
                'U','V','W','X','Y','Z'
        };
        int n2 = n * n;
        int n3 = n * n * n;
        int len2 = 0, temp2 = n2;
        while (temp2 > 0) { len2++; temp2 >>>= 4; } 
        int len3 = 0, temp3 = n3;
        while (temp3 > 0) { len3++; temp3 /= 36; }

        char[] buffer = new char[len2 + len3];

        int idx = buffer.length - 1;
        while (n3 > 0) {
            buffer[idx--] = DIGITS[n3 % 36];
            n3 /= 36;
        }
        while (n2 > 0) {
            buffer[idx--] = DIGITS[n2 & 15];
            n2 >>>= 4;
        }
        return new String(buffer);
    }
}