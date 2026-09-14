class Solution {
    public int[] executeInstructions(int n, int[] st, String s) {
        int m = s.length(), h = m + n, v = m + n;
        int[] hor = new int[(m + n) * 2];
        int[] ver = new int[(m + n) * 2];
        int[] res = new int[m];
        char[] str = s.toCharArray();
        for (int i = 0; i < (m + n) * 2; i++) {
            hor[i] = m;
            ver[i] = m;
        }

        for (int i = m - 1; i >= 0; --i) {
            hor[h] = i;
            ver[v] = i;

            if (str[i] == 'L') h += 1;
            else if (str[i] == 'R') h -= 1;

            if (str[i] == 'U') v += 1;
            else if (str[i] == 'D') v -= 1;

            int val = Math.min(Math.min(Math.min(hor[h - st[1] - 1], hor[h - st[1] + n]),ver[v - st[0] - 1]),ver[v - st[0] + n]);
            res[i] = Math.min(m, val) - i;
        }
        return res;
    }
}