class Solution {
    public String finalString(String s) {
        int ci = 0 ; 
        char[] a = new char[s.length()]; 
        for ( var e : s.toCharArray()) {
            if ( e == 'i') {
                int l = 0 ; 
                int r = ci -1; 
                while ( l < r) {
                    char temp = a[l]; 
                    a[l] = a[r]; 
                    a[r] = temp; 
                    l++; 
                    r--; 
                }

            }else {
                a[ci++] = e; 
            }
        }
        return new String(a , 0 , ci);
    }
}