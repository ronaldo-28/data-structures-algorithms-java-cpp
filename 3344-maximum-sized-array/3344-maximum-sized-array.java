class Solution {

    long getOdds(int n, int i) {
        int cnt = 1 << i;
        int mod = 1 << (1 + i);
        int period = n/mod;
        int rem = n % mod;
        return period * cnt + (rem > cnt ? rem - cnt : 0);
    }

    long count(int n) {
        long res = 0;
        for(int i = 0; (1<<i) < n; i++) {
            long odds = getOdds(n, i);
            long evens = n - odds;
            res += (n * n - evens * evens) * (1 << i);
        }
        return res * (n - 1) * n/2;
    }

    public int maxSizedArray(long s) {
        int l = (int)Math.pow(4*s, 0.2)/2, r = (int)Math.sqrt(2 * Math.sqrt(s)) + 2;
        while(l < r) {
            int mid = (l + r)/2;
            if(count(mid) > s) {
                r = mid;
            }else {
                l = mid + 1;
            }
        }
        return l-1;
    }
}