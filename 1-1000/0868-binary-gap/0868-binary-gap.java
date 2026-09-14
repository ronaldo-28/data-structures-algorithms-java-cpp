class Solution {
    public int binaryGap(int n) {
        int m = 0;
        int l = -1;
        int p =0;
        while(n>0){
            if((n&1)==1){
                if(l !=-1){
                    int d = p-l;
                    m = Math.max(m,d);
                }
                l = p;
            }
            n = n>>1;
            p++;
        }
        return m;
    }
}