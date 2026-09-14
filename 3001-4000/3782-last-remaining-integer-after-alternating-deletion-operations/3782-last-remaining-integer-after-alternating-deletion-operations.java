class Solution {
    public long lastInteger(long n) {
        long mask = 0xAAAAAAAAAAAAAAAAL;
        return 1 + (mask & (n - 1)); 
    }
}