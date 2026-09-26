class Solution {
    static int mod = (int)1e9 + 7;
    static int N = 16;
    static long[] pow10 = new long[N];
    public int sumDecoded(long[] nums) {

        if(pow10[0] == 0){
            pow10[0] = 1;
            for(int i = 1; i < N; ++i)
                pow10[i] = pow10[i - 1] * 10;
        }

        long sum = 0;
        for(long num:nums){

            int w = (int) (num % 10);
            long v = num / 10;

            int len = 1;
            while(v >= pow10[len])
                len++;
            
            long base =  pow10[len - w];
            long x = v /base, y = v % base;
            sum += pow(x, y);
        }

        return (int)(sum % mod);
    }

    long pow(long x, long n){
        long result = 1;
        while(n > 0){
            if((n & 1) == 1)
                result = result * x % mod;
            
            x = x * x % mod;
            n >>= 1;
        }

        return result;
    }
}