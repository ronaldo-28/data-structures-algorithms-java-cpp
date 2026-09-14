class Solution {
    public int almostPalindromic(String s) {
        char str[] = s.toCharArray();
        int ans = 0;
        for (int i = 0, n = str.length; i < n; i++) {
            int j = i - 1, k = i + 1;
            while (j >= 0 && k < n && str[j] == str[k]) {
                j--;
                k++;
            }

            if (j == -1 && k == n) return n;

            int j1 = j, k1 = k + 1;
            while (j1 >= 0 && k1 < n && str[j1] == str[k1]) {
                j1--;
                k1++;
            }

            ans = Math.max(ans, k1 - j1 - 1);

            int j2 = j - 1, k2 = k;
            while (j2 >= 0 && k2 < n && str[j2] == str[k2]) {
                j2--;
                k2++;
            }

            ans = Math.max(ans, k2 - j2 - 1);

            int l = i, h = i + 1;
            while (l >= 0 && h < n && str[l] == str[h]) {
                l--;
                h++;
            }

            if (l == -1 && h == n) return n;

            int l1 = l, h1 = h + 1;
            while (l1 >= 0 && h1 < n && str[l1] == str[h1]) {
                l1--;
                h1++;
            }

            ans = Math.max(ans, h1 - l1 - 1);

            int l2 = l - 1, h2 = h;
            while (l2 >= 0 && h2 < n && str[l2] == str[h2]) {
                l2--;
                h2++;
            }

            ans = Math.max(ans, h2 - l2 - 1);
        }

        return ans;
    }
}