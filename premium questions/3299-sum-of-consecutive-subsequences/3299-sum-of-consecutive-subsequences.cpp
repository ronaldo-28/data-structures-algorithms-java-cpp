class Solution {
      const int M = 1000000007;
    void add(int &x, int y) {
        if ((x += y) >= M) {
            x -= M;
        }
    }
    
    void sub(int &x, int y) {
        if ((x -= y) < 0) {
            x += M;
        }
    }

    int mul(long long x, long long y) {
        return x * y % M;
    }

    int get(const unordered_map<int, int>& dp, int x) {
        const auto t = dp.find(x);
        return t == dp.end() ? 0 : t->second;
    }

    int get(const vector<int> &nums, int d) {
        unordered_map<int, int> en;
        const int n = nums.size();
        vector<int> c(n, 1);
        for (int i = 0; i < n; ++i) {
            add(c[i], get(en, nums[i] - d));
            add(en[nums[i]], c[i]);
        }
        unordered_map<int, int> be;
        int r = 0;
        for (int i = nums.size() - 1; i >= 0; --i) {
            int temp = 1;
            add(temp, get(be, nums[i] + d));
            add(be[nums[i]], temp);
            add(r, mul(nums[i], mul(c[i], temp)));
        }
        return r;
    }
public:
    int getSum(vector<int>& nums) {
        int r = 0;
        add(r, get(nums, -1));
        add(r, get(nums, 1));
        for (int x : nums) {
            sub(r, x);
        }
        return r;
    }
};