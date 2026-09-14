class Solution {
    public String maximumXor(String s, String t) {
        String sel = s;

        int cnt[] = new int[2];
        for (int i = 0; i < t.length(); i++) {
            cnt[t.charAt(i) - '0']++;
        }

        char res[] = new char[sel.length()];

        int i = 0;
        while (i < sel.length()) {
            int bit = sel.charAt(i) - '0';
            int ne = 1 - bit;

            if (cnt[ne] > 0) {
                res[i] = '1';
                cnt[ne]--;
            } else {
                res[i] = '0';
                cnt[bit]--;
            }
            i++;
        }

        return new String(res);
    }
}