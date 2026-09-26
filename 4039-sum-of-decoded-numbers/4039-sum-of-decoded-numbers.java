class Solution {
    public int sumDecoded(long[] nums) {
        int n=nums.length;
        long  sum=0;
        long MOD = 1000000007L;
        for(int i=0;i<n;i++){
            long width =nums[i] %10;
            long d = nums[i] / 10;

            long t = d;
            int digits = 0;
            while (t>0) {
                digits++;
                t /= 10;
            }
            
            long divisor =1;
            for(int j=0;j<digits-width;j++){
                divisor *= 10;
            }

            long y = d % divisor;
            long x = d / divisor;

            long power = calculatePower(x, y, MOD);
            sum = (sum + power) % MOD;
        }
        return (int)sum;
    }
    private long calculatePower(long x, long y, long MOD) {
        long result = 1;
        while (y > 0) {
            if (y % 2 == 1) {
                result = (result * x) % MOD;
            }
            x = (x * x) % MOD;
            y = y / 2;
        }
        return result;
    }
}