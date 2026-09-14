class Solution {
    Boolean[][] memo;

    private boolean helper(String s1, int i, String s2, int j, String s3) {
        if (i + j == s3.length()) return true;

        if (memo[i][j] != null) return memo[i][j];

        boolean res = false;

        if (i < s1.length() && s1.charAt(i) == s3.charAt(i + j)) {
            res = helper(s1, i + 1, s2, j, s3);
        }
        if (!res && j < s2.length() && s2.charAt(j) == s3.charAt(i + j)) {
            res = helper(s1, i, s2, j + 1, s3);
        }

        return memo[i][j] = res;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        memo = new Boolean[s1.length() + 1][s2.length() + 1];
        return helper(s1, 0, s2, 0, s3);
    }
}