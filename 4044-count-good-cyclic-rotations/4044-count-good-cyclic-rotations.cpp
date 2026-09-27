class Solution {
public:
    int countGoodRotations(vector<int>& nums) {
        long long int total = accumulate(nums.begin(), nums.end(), 0ll);
        int n = nums.size();
        vector<long long int> prefix(n + 1, 0);
        for(int i = 1;i <= n;i++){
            prefix[i] = prefix[i-1] + nums[i-1];
        }
        int sol = 0;
        for(int i = n/2+1;i<=n;i++){
            long long int l = prefix[i] - prefix[i - n/2];
            long long int r = total - l;
            if(l != r){
                sol++;
            }
        }
        return sol;
    }
};