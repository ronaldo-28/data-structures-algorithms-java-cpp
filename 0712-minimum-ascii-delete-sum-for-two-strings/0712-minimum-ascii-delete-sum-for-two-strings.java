class Solution {
    public int minimumDeleteSum(String s11, String s22) {
        char[] s1 = s11.toCharArray(), s2 = s22.toCharArray();
        int m = s1.length, n = s2.length, total = 0;
        int[] prev = new int[n + 1], cur = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            total += s1[i - 1];
            for (int j = 1; j <= n; j++) {
                if (s1[i - 1] == s2[j - 1])
                    cur[j] = s1[i - 1] + prev[j - 1];
                else
                    cur[j] = Math.max(cur[j - 1], prev[j]);
            }
            int[] temp = prev;
            prev = cur;
            cur = temp;
        }

        for (char c : s2)
            total += c;

        return total - 2 * prev[n];
    }
}