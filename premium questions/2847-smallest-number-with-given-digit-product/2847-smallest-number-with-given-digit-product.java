public class Solution {
    public String smallestNumber(long n) {
        if (n == 1) {
            return "1";
        }
        
        final int[] p = {2, 3, 5, 7};
        int[] v = new int[10];
        
        for (int x : p) {
            while (n % x == 0) {
                n /= x;
                v[x]++;
            }
        }
        
        if (n > 1) {
            return "-1";
        }
        
        v[9] = v[3] >> 1;
        v[3] &= 1;
        v[8] = v[2] / 3;
        v[2] %= 3;
        
        if (v[3] > 0 && v[2] > 0) {
            v[2]--;
            v[3] = 0;
            v[6] = 1;
        }
        
        v[4] = v[2] >> 1;
        v[2] &= 1;
        
        StringBuilder r = new StringBuilder();
        for (int i = 1; i < 10; ++i) {
            char c = (char) (i + '0');
            while (v[i]-- > 0) {
                r.append(c);
            }
        }
        
        return r.toString();
    }   
}