class Solution {
public:
using ll = long long;
    long long maxScore(vector<int>& nums) {
        int n = nums.size();
        vector<ll> pre1(n),pre2(n);
        vector<ll> suff1(n),suff2(n);

        pre1[0] = nums[0];
         pre2[0] = nums[0];
        for(int i = 1 ; i < n ; i++){
            pre1[i] = lcm(pre1[i-1],nums[i]);
            pre2[i] = gcd(pre2[i-1],nums[i]);
        }

        suff1[n-1] = nums[n-1];
        suff2[n-1] = nums[n-1];
        for(int i = n-2 ; i>= 0 ; i--){
            suff1[i] = lcm(suff1[i+1] , nums[i]);
            suff2[i] = gcd(suff2[i+1] , nums[i]);
        }

       ll maxi = pre1[n-1] * pre2[n-1];

              if(n == 1){
              return maxi;
              }
        for(int i = 0 ; i < n ; i++){
          ll x,y ;
          if(i==0){
            x = suff1[i+1];
            y = suff2[i+1];
          }
          else if(i==n-1){
            x = pre1[i-1];
            y = pre2[i-1];
          }
          else{
            x = lcm(pre1[i-1],suff1[i+1]);
            y = gcd(pre2[i-1],suff2[i+1]);
          }
          maxi = max(maxi, x*y);
        }
        return maxi;
    }
};