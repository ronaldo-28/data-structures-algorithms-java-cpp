class Solution {
    public boolean consecutiveSetBits(int n) {
        int x = n & (n >> 1);
        if(x > 0 && ((x & (x-1)) == 0)) return true;
        else return false;
    }
}