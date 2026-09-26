class Solution {
    public int countRotations(String s, int k) {
        int n = s.length(), same = 0;
        for (int i = 0; i < n; i++)
            if (s.charAt(i) == s.charAt((i + 1) % n)) same++;
        return k == same - 1 ? same : k == same ? n - same : 0;
    }
}