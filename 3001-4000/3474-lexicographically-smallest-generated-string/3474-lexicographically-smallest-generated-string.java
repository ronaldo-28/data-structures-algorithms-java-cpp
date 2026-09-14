// https://www.youtube.com/@0x3f
class Solution {
    public String generateString(String S, String t) {
        char[] s = S.toCharArray();
        int n = s.length;
        int m = t.length();
        char[] ans = new char[n + m - 1];
        Arrays.fill(ans, '?');

        // 处理 T
        int[] z = calcZ(t);
        int pre = -m;
        for (int i = 0; i < n; i++) {
            if (s[i] != 'T') {
                continue;
            }
            int size = Math.max(pre + m - i, 0);
            // t 的长为 size 的前后缀必须相同
            if (size > 0 && z[m - size] < size) {
                return "";
            }
            // size 后的内容都是 '?'，填入 t
            for (int j = size; j < m; j++) {
                ans[i + j] = t.charAt(j);
            }
            pre = i;
        }

        // 计算 <= i 的最近待定位置
        int[] preQ = new int[ans.length];
        pre = -1;
        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == '?') {
                ans[i] = 'a'; // 待定位置的初始值为 a
                pre = i;
            }
            preQ[i] = pre;
        }

        // 找 ans 中的等于 t 的位置，可以用 KMP 或者 Z 函数
        z = calcZ(t + new String(ans));

        // 处理 F
        for (int i = 0; i < n; i++) {
            if (s[i] != 'F') {
                continue;
            }
            // 子串必须不等于 t
            if (z[m + i] < m) {
                continue;
            }
            // 找最后一个待定位置
            int j = preQ[i + m - 1];
            if (j < i) { // 没有
                return "";
            }
            ans[j] = 'b';
            i = j; // 直接跳到 j
        }

        return new String(ans);
    }

    private int[] calcZ(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[] z = new int[n];
        int boxL = 0; // z-box 左右边界（闭区间）
        int boxR = 0;
        for (int i = 1; i < n; i++) {
            if (i <= boxR) {
                z[i] = Math.min(z[i - boxL], boxR - i + 1);
            }
            while (i + z[i] < n && s[z[i]] == s[i + z[i]]) {
                boxL = i;
                boxR = i + z[i];
                z[i]++;
            }
        }
        z[0] = n;
        return z;
    }
}