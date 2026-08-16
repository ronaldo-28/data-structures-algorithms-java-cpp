class Solution {
    public String reorganizeString(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) freq[c - 'a']++;

        // 找最大频率字符
        int maxFreq = 0, maxChar = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > maxFreq) {
                maxFreq = freq[i];
                maxChar = i;
            }
        }

        // 可行性判断
        if (maxFreq > (n + 1) / 2) return "";

        char[] res = new char[n];
        int idx = 0;

        // 1️⃣ 先填最大频率字符（偶数位）
        while (freq[maxChar]-- > 0) {
            res[idx] = (char) (maxChar + 'a');
            idx += 2;
        }

        // 2️⃣ 填其他字符
        for (int i = 0; i < 26; i++) {
            while (freq[i]-- > 0) {
                if (idx >= n) idx = 1; // 切换到奇数位
                res[idx] = (char) (i + 'a');
                idx += 2;
            }
        }

        return new String(res);
    }
}
