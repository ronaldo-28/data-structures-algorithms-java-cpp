class Solution {
public:
    int minOperations(vector<int>& nums) {
        int n = nums.size();
        if(n < 3) return -1;

        int hi = *max_element(nums.begin(), nums.end()) + n;
        int inf = 1e9;

        vector<vector<int>> left(n, vector<int>(hi + 1, inf));

        for(int v = 1; v <= hi; v++) left[0][v] = abs(nums[0] - v);

        for(int i = 1; i < n; i++) {
            int best = inf;
            for(int v = 1; v <= hi; v++) {
                best = min(best, left[i - 1][v - 1]);
                if(best < inf) left[i][v] = best + abs(nums[i] - v);
            }
        }

        vector<int> nxt(hi + 1, inf);

        for(int v = 1; v <= hi; v++) nxt[v] = abs(nums[n - 1] - v);

        int res = inf;

        for(int i = n - 2; i >= 1; i--) {
            vector<int> cur(hi + 1, inf);
            int best = inf;

            for(int v = 1; v <= hi; v++) {
                best = min(best, nxt[v - 1]);
                if(best < inf) cur[v] = best + abs(nums[i] - v);
            }

            for(int v = 1; v <= hi; v++) {
                if(left[i][v] < inf and cur[v] < inf) {
                    res = min(res, left[i][v] + cur[v] - abs(nums[i] - v));
                }
            }

            nxt = cur;
        }

        return res;
    }
};