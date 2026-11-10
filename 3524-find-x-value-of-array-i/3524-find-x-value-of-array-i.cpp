class Solution {
public:
    vector<long long> resultArray(vector<int>& nums, int k) {
        for (int& x: nums) {
            x = x % k;
        }
        int n = nums.size();
        if (k == 1) {
            vector<long long> ans = {(long long)n * (n + 1) / 2};
            return ans;
        }
        vector<int> opp(k, -1);
        for (int target = 1; target < k; target++) {
            for (int cand = 1; cand < k; cand++) {
                if (target * cand % k == 1) {
                    opp[target] = cand;
                }
            }
        }

        vector<int> cnt(k);
        vector<long long> ans(k);
        cnt[1] = 1;
        int cur = 1;
        for (int x: nums) {
            if (x == 0 || (x == 2 && k == 4)) {
                cur = 1;
                fill(cnt.begin(), cnt.end(), 0);
                cnt[1] = 1;
                continue;
            }
            cur = cur * x % k;
            // printf("x = %d cur = %d\n", x, cur);
            for (int target = 1; target < k; target++) {
                if (opp[target] != -1) {
                    int prev = cur * opp[target] % k;
                    ans[target] += cnt[prev];
                }
            }
            cnt[cur]++;
        }
        if (k == 4) {
            for (int i = 0; i < n; i++) {
                if (nums[i] == 2) {
                    int l = i;
                    while (l > 0 && nums[l - 1] != 2 && nums[l - 1] != 0) {
                        l--;
                    }
                    int r = i;
                    while (r + 1 < n && nums[r + 1] != 2 && nums[r + 1] != 0) {
                        r++;
                    }

                    ans[2] += (long long)(i - l + 1) * (r - i + 1);
                }
            }
            ans[0] = (long long)n * (n + 1) / 2 - ans[1] - ans[2] - ans[3];
        } else {
            int prev_zero = -1;
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    ans[0] += (long long)(i - prev_zero) * (n - i);
                    prev_zero = i;
                }
            }
        }
        return ans;
    }
};