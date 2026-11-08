class Solution {
        public boolean repeatedStringMatch(
            String a, String b, int x, int y) {
        int n = a.length(), m = b.length();
        String s = b.repeat((int) Math.min(y, n / m + 2));
        long end = Math.min(n - 1L, 1L * n * x - 1L * m * y)
                + s.length();
        if (end < s.length()) return false;
        int k = (int) ((end + n - 1) / n);
        return a.repeat(k).substring(0, (int) end).contains(s);
    }
}