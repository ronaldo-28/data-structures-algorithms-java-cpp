class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        long min=-1000000000000000000L;
        long a=min;
        long b=min;
        long m=min;
        long d=min;
        long res=min;
        for(int num: nums){
            long div=(long)(num/k);
            long mul=(long)num*k;
            long max1=Math.max(0,a);
            long max2=Math.max(m,d);
            long A=num+max1;
            long B=num+Math.max(b,max2);
            long M=mul+Math.max(m,max1);
            long D=div+Math.max(d,max1);
            a=A;
            b=B;
            m=M;
            d=D;
            res=Math.max(res,Math.max(b, Math.max(m,d)));
        }
        return res;
        
    }
}