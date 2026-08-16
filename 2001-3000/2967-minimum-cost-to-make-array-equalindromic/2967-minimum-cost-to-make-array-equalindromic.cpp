class Solution {
private:
    static inline bool isPal(int x) {
        int rev = 0, orig = x;
        while (x) {
            rev = rev * 10 + (x % 10);
            x /= 10;
        }
        return rev == orig;
    }
    static inline int nextPal(int x) {
        for (int i = x; i < 1000000000; ++i) {
            if (isPal(i))
                return i;
        }
        return 999999999;
    }
    static inline int prevPal(int x) {
        for (int i = x; i; --i) {
            if (isPal(i))
                return i;
        }
        return 1;
    }

public:
    long long minimumCost(vector<int>& nums) {
        const int n = nums.size();
        nth_element(nums.begin(), nums.begin() + n / 2, nums.end());
        int median = nums[n / 2];
        int lower = prevPal(median);
        int upper = nextPal(median);
        long long costLower = 0, costUpper = 0;
        for (int i = 0; i < n; ++i) {
            costLower += abs(nums[i] - lower);
            costUpper += abs(nums[i] - upper);
        }
        return min(costLower, costUpper);
    }
};