class Solution {
    public int minimumDeletions(String s) {

        int n = s.length();
        byte[] cs = new byte[n];
        s.getBytes(0, n, cs, 0);

        int dp0 = 0, dp1 = 0;
        for(byte c:cs){
            dp0 += 'b' - c;
            dp1 += c - 'a';
            dp1 = Math.max(dp1, dp0);
        }

        return n - dp1;
    }
}