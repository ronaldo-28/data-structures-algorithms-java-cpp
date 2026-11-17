typedef long long ll;
class Solution {
public:
    ll solve(int k, vector<int> nums) {
        int n = nums.size();
        vector<int> ans;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == k) ans.push_back(i);
        }
        int needed = (n + 1) / 2;
        if ((int)ans.size() < needed) return LLONG_MAX;

        ll cnt = 0;
        for (int i = 0; i < needed; i++) {
            cnt += llabs(ans[i] - 2 * i);
        }
        return cnt;
    }

    int minSwaps(vector<int>& nums) {
        int evens = 0;
        for (auto it : nums) evens += (it % 2 == 0);
        int odds = nums.size() - evens;
        if (abs(evens - odds) > 1) return -1;
        ll ans = min(solve(0, nums), solve(1, nums));
        return ans == LLONG_MAX ? -1 : int(ans);
    }
};