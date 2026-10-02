class Solution {
    public int numberOfSpecialChars(String w) {
        byte[] a = w.getBytes();
        int[] l = new int[26];
        int[] u = new int[26];
        
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= 'a') {
                l[a[i] - 'a'] = i + 1;
            } else if (u[a[i] - 'A'] == 0) {
                u[a[i] - 'A'] = i + 1;
            }
        }
        
        int r = 0;
        for (int i = 0; i < 26; i++) {
            if (l[i] > 0 && u[i] > 0 && l[i] < u[i]) {
                r++;
            }
        }
        
        return r;
    }
}