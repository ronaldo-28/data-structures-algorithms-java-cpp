class Solution {
    public int compress(char[] chars) {
        int writeIndex = 0;
        int n = chars.length;
        int p = 1, count = 1;
        char startChar = chars[0];
        while (p < n) {
            if (chars[p] == startChar) {
                count++;
            } else {
                chars[writeIndex++] = startChar;
                if (count <= 9) {
                    if (count != 1)
                        chars[writeIndex++] = (char) (count + '0');
                } else {
                    String s = Integer.toString(count);
                    for (int i = 0; i < s.length(); i++) {
                        chars[writeIndex++] = s.charAt(i);
                    }
                }
                startChar = chars[p];
                count = 1;
            }
            p++;
        }
        chars[writeIndex++] = startChar;
        if (count <= 9) {
            if (count != 1)
                chars[writeIndex++] = (char) (count + '0');
        } else {
            String s = Integer.toString(count);
            for (int i = 0; i < s.length(); i++) {
                chars[writeIndex++] = s.charAt(i);
            }
        }
        return writeIndex;
    }
}