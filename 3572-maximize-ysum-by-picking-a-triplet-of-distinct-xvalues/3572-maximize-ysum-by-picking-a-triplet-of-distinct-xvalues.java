class Solution {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length, a = -1, b = -1, c = -1;
        for(int i = 0; i < n; ++i) {
            if(a < 0 || y[i] > y[a]) {
                if(a >= 0 && x[i] == x[a]) {
                    a = i;
                } else if(b >= 0 && x[i] == x[b]) {
                    b = a;
                    a = i;
                } else {
                    c = b;
                    b = a;
                    a = i;
                }
            } else if(b < 0 || y[i] > y[b]) {
                if(b >= 0 && x[i] == x[b]) {
                    b = i;
                } else if((c >= 0 && x[i] == x[c]) || x[i] != x[a]) {
                    c = b;
                    b = i;
                }
            } else if(c < 0 || y[i] > y[c]) {
                if(x[i] != x[b] && x[i] != x[a]) {
                    c = i;
                }
            }
        }
        return a < 0 || b < 0 || c < 0 ? -1 : y[a] + y[b] + y[c];
    }
}