class Solution {
    static {
        for(int i = 0; i < 500; i++) equalSubstring("", "", 1);
    }
    public static int equalSubstring(String s, String t, int maxCost) {
        int n = s.length(), l = 0, res = 0, window = 0;
        int[] diff = new int[n];
        for(int i = 0; i < n; i++) diff[i] = Math.abs(s.charAt(i) - t.charAt(i));

        for(int r = 0; r < n; r++) {
            // System.out.println(">"+s.substring(l,r+1));
            window += diff[r];
            while(r >= l && window > maxCost ) {
                window -= diff[l++];
                // System.out.println(s.substring(l,r+1)+"<");
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}