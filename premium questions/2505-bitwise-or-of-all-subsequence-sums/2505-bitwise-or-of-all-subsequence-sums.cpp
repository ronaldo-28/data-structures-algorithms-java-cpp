#define ll long long int
class Solution {
public:
    long long subsequenceSumOr(vector<int>& nums) {
        ll i,j,n,t,k,m;
        ll ans = 0;
        ll sum = 0;

        for(auto num: nums) {
            ans |= num;
            sum+= num;
            ans|=sum;
        }
        return ans;

    }
};