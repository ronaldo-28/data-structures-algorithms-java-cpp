class Solution {
    public String[] divideString(String s, int k, char fill) {
        int n = s.length();
        if(n < k) {
            char[] ans = new char[k];
            for(int i=0; i<k; i++) {
                if(i < n) ans[i] = s.charAt(i);
                else {
                    ans[i] = fill;
                }
            }
            return new String[]{ new String(ans) };
        }
        int c = n/k;
        int count = c;
        int extra = 0;
        if(n % k != 0) {
            extra = 1;
        }
        int i = 0, j = 0;
        String[] ans = new String[(n/k) + extra];
        while(c > 0) {
            ans[i++] = s.substring(j,j+k);
            j = j+k;
            c--;
        }
        if(1 == extra) {
            int temp = (n % k);
            char[] ch = new char[k];
            for(int l=0; l<k; l++) {
                if(temp != 0) {
                    ch[l] = s.charAt(j++);
                    temp--;
                }
                else ch[l] = fill;
            }
            ans[i++] = new String(ch);
        }
        
        return ans;
    }
}