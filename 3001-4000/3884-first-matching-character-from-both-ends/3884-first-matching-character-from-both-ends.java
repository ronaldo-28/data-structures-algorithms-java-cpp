class Solution {
    public int firstMatchingIndex(String s) {
        int l = 0, h = s.length() - 1;
        
        while (l <= h) {
            if (s.charAt(l) == s.charAt(h)) {
                return l;
            }
            l++;
            h--;
        }
        
        return -1;
    }
}