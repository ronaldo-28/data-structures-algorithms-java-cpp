class Solution {
    public long GCD(long a,long b){
        while(b!=0){
            long temp=a%b;
            a=b;
            b=temp;
        }
        return a;
    }
    public long LCM(long a,long b){
        return (a/GCD(a,b))*b;
    }
    public long maxScore(int[] nums) {
        int n=nums.length;
        if(n==1) return (long)nums[0]*nums[0];
        long [] prefixLcm = new long[n];
        long [] prefixGcd = new long[n];

        long [] suffixLcm = new long[n];
        long [] suffixGcd = new long[n];

        prefixLcm[0]=nums[0];
        prefixGcd[0]=nums[0];
        for(int i=1;i<n;i++){
            prefixLcm[i]=LCM(prefixLcm[i-1],nums[i]);
            prefixGcd[i]=GCD(prefixGcd[i-1],nums[i]);
        }
        suffixLcm[n-1]=nums[n-1];
        suffixGcd[n-1]=nums[n-1];
        for(int i=n-2;i>=0;i--){
            suffixLcm[i]=LCM(suffixLcm[i+1],nums[i]);
            suffixGcd[i]=GCD(suffixGcd[i+1],nums[i]);
        }
        long ans=prefixLcm[n-1]*prefixGcd[n-1];
        for(int i=0;i<n;i++){
            long Lcm;
            long Gcd;
            if(i==0){
               Lcm=suffixLcm[1];
               Gcd=suffixGcd[1];
            }else if(i==n-1){
                Lcm=prefixLcm[n-2];
                Gcd=prefixGcd[n-2];
            }else{
                Lcm=LCM(prefixLcm[i-1],suffixLcm[i+1]);
                Gcd=GCD(prefixGcd[i-1],suffixGcd[i+1]);
            }
            ans=Math.max(ans,Lcm*Gcd);
        }
        return ans;
    }
}