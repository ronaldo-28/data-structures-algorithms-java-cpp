class Solution {
public:
long long gcd(long long a,long long b){
    if(b==0){
        return a;
    }
    return gcd(b,a%b);
}
long long lcm(long long a,long long b){
    return (a/gcd(a,b))*b;
}
    long long maxScore(vector<int>& nums) {
        int n=nums.size();
        vector<long long >prefg(n);
        vector<long long >prefl(n);
        vector<long long>suffg(n);
        vector<long long>suffl(n);
        if(n==1){
            return nums[0]*nums[0];
        }
        prefg[0]=nums[0];
        prefl[0]=nums[0];
        suffg[n-1]=nums[n-1];
        suffl[n-1]=nums[n-1];
        for(int i=1;i<n;i++){
            prefg[i]=gcd(prefg[i-1],nums[i]);
            prefl[i]=lcm(prefl[i-1],nums[i]);
        }
        for(int i=n-2;i>=0;i--){
            suffg[i]=gcd(suffg[i+1],nums[i]);
            suffl[i]=lcm(suffl[i+1],nums[i]);
        }
           long long ans=(long long)prefg[n-1]*prefl[n-1];
        for(int i=0;i<n;i++){
            long long l;
            long long g;
            if(i==0){
               l=suffl[1];
               g=suffg[1];
            }
            else if(i==n-1){
                l=prefl[n-2];
                g=prefg[n-2];
            }
            else{
                g=gcd(prefg[i-1],suffg[i+1]);
                l=lcm(prefl[i-1],suffl[i+1]);
            }
            ans=max(ans,(long long)l*g);
        }
        return ans;
    }
};