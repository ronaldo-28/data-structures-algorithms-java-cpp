class Solution {
    int gcd(int a, int b) { return b == 0 ? a : gcd(b, a%b); }
    int lcm(int a, int b) { return a / gcd(a, b) * b; }

    int count(int[] nums, int k, int ss, int ee) {
        if ( ss > ee ) return 0;

        int result = 0;
        while (ss <= ee) {
            int lcm = nums[ss];
            int gcd = nums[ss];
            for ( int ii = ss; ii <= ee; ii++ ) {
                int num = nums[ii];
                gcd = gcd(gcd, num);
                lcm *= num / gcd;
                if ( lcm == k ) {
                    result += ee - ii + 1;
// System.out.printf("ss=%d, ee=%d, ii=%d, result=%d\n", ss, ee, ii, result);
                    break;
                }
            }
            if ( lcm != k ) break;
            ss++;
        }
        return result;
    }
    public int subarrayLCM(int[] nums, int k) {
        int N = nums.length;

        int sIdx = 0;
        int result = 0;
        for ( int ii = 0; ii < N; ii++ ) {
            int num = nums[ii];
            if ( k % num != 0 ) {
                result += count(nums, k, sIdx, ii - 1);
                sIdx = ii + 1;
            }
        }
        result += count(nums, k, sIdx, N - 1);
        return result;
    }
}