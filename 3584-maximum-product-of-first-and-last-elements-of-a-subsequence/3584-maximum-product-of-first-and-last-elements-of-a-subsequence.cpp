class Solution {
public:
    using ll=long long;
    long long maximumProduct(vector<int>& nums, int m) {
        int n=nums.size();
        if(m==1)
        {
            ll ans=LLONG_MIN;
            for(int i=0; i<n; i++)
            {
                 ans=max(ans,nums[i]*1LL*nums[i]);

            }
            return ans;
        }
        ll pmin=LLONG_MIN;
        ll pmax=LLONG_MAX;
        int st=0;
        int en=m-1;
        pmin=nums[st];
        pmax=nums[st];
        ll ans=LLONG_MIN;
        while(en<n)
        {
            pmin=min(pmin,1LL*nums[st]);
            pmax=max(pmax,1LL*nums[st]);
             ans=max(ans,nums[en]*pmin);
             ans=max(ans,nums[en]*pmax);
             st++;en++;

        }
        return ans;
    }
};