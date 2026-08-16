class Solution {
public:
    int minSizeSubarray(vector<int>& nums, int target) {

        long long total = 0;
        int n = nums.size();

        for (int x : nums)
            total += x;

        long long full = target / total;
        long long rem = target % total;

        if (rem == 0)
            return full * n;

        long long sum = 0;
        int i = 0;
        int ans = INT_MAX;

        for (int j = 0; j < 2 * n; j++) {

            sum += nums[j % n];

            while (sum > rem) {
                sum -= nums[i % n];
                i++;
            }

            if (sum == rem) {
                ans = min(ans, j - i + 1);
            }
        }

        if (ans == INT_MAX)
            return -1;

        return full * n + ans;
    }
};