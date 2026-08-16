class Solution {
    public String baseNeg2(int n) {
        int a = 1;
        while(a<n){
            a=(a<<2)+1;
        }
        return Integer.toBinaryString(a^(a-n));
    }
}