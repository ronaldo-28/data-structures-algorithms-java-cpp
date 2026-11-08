class Solution {
        public long countValidSubstrings(String s) {
        String[] parts = s.replace("abc", "ab bc").split(" ");
        long res = -(parts.length - 1);
        for (String p : parts) {
            long n = p.length();
            res += n * (n + 1) / 2;
        }
        return res;
    }
}