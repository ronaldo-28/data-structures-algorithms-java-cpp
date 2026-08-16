class Solution {
    public int countSubstrings(String s) {
        int n = s.length(), res = 0;
        final char[] chars = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int j = i - 1, k = i;
            while (k < n - 1 && chars[k] == chars[k + 1]) k++;
            res += (k - j) * (k - j + 1) / 2;
            i = k++;
            while (j >= 0 && k < n && chars[k++] == chars[j--]) res++;
        }

        return res;
    }
}