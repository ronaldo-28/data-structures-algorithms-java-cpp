class Solution {
    public String generateTag(String caption) {
        char[] s = caption.toCharArray();
        int n = s.length;
        int diff = 'a' - 'A';
        boolean hasFirst = false;
        boolean hasNextFirst = false;
        int spaces = 0;
        for (int i = 0; i < n; i++) {
            char c = s[i];
            if (c == ' ') {
                hasNextFirst = false;
                spaces++;
            } else if (c >= 'a') {
                if (!hasNextFirst) {
                    if (hasFirst) s[i] = (char)(c - diff);
                    else hasFirst = true;
                    hasNextFirst = true;
                }
            } else {
                if (!hasNextFirst) {
                    if (!hasFirst) {
                        hasFirst = true;
                        s[i] = (char)(c + diff);
                    }
                    hasNextFirst = true;
                } else {
                    s[i] = (char) (c + diff);
                }
            }
        }
        int m = Math.min(100, n - spaces + 1);
        char[] res = new char[m];
        res[0] = '#';
        int idx = 1;
        for (int i = 0; i < n && idx < m; i++) {
            if (s[i] != ' ') res[idx++] = s[i];
        }
        return String.valueOf(res);
    }
}