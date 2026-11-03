class Solution {
    public boolean maxSubstringLength(String s, int k) {
        char[] chs = s.toCharArray();
        int n = chs.length;
        int[] dp = new int[n];
        int[] begin = new int[26], end = new int[26];
        Arrays.fill(begin, -1);
        for (int i = 0; i < n; i++) {
            int j = chs[i] - 'a';
            if (begin[j] == -1) {
                begin[j] = i;
            }
            end[j] = i;
        }
        for (int i = 0; i < n; i++) {
            dp[i] = i > 0 ? dp[i - 1] : 0;
            int idx = chs[i] - 'a';
            if (end[idx] == i) {
                int left = begin[idx], j = i;
                while (j >= left) {
                    idx = chs[j] - 'a';
                    if (end[idx] > i) {
                        break;
                    }
                    left = Math.min(left, begin[idx]);
                    j--;
                }
                if (j == left - 1 && !(i == n - 1 && j == -1)) {
                    dp[i] = Math.max(dp[i], j >= 0 ? dp[j] + 1 : 1);
                }
            }
        }
        return dp[n - 1] >= k;
    }
}