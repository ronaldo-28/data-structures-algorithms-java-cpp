class Solution {
    public int minMovesToMakePalindrome(String s) {
        int l = 0, r = s.length() - 1, ans = 0;
        char[] c = s.toCharArray();
        
        while (l < r) {
            if (c[l] == c[r]) { // Already matching, no operation needed
                l++;
                r--;
            } else {
                int k = r;
                while (k > l && c[k] != c[l]) {
                    k--;  // Find matching character for c[l]
                }
                
                if (k == l) { // Edge case: No match, move to center
                    swap(c, l, l + 1);
                    ans++;
                } else { // Move character at k to position r
                    shiftRight(c, k, r);
                    ans += (r - k);
                    l++;
                    r--;
                }
            }
        }
        return ans;
    }

    private void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    private void shiftRight(char[] c, int start, int end) {
        char temp = c[start];
        System.arraycopy(c, start + 1, c, start, end - start);
        c[end] = temp;
    }
}