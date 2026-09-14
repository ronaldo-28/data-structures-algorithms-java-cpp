class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX_ENTRIES = 100_001;
    private static final long[] cache = new long[MAX_ENTRIES];
    private static int _latest = 1;

    public int shift(int n){
        return 32 - Integer.numberOfLeadingZeros(n);
    }

    public int concatenatedBinary(int n) {
        if (n < _latest) return (int) cache[n];
        long res = cache[_latest - 1];
        for (int i = _latest; i <= n; i++){
            res = ((res << shift(i)) | i) % MOD;
            cache[_latest++] = res;
        }
        return (int) res;
    }  
}