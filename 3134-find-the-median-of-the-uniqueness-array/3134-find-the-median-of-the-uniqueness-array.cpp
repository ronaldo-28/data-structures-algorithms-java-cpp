int freq[100001] = {};
class Solution {
    long long check(vector<int>& nums, int k) {
        const int n = nums.size();
        long long res = 0;
        int i = 0;
        for(int j = 0, d = 0; j < n; ++j) {
            if(freq[nums[j]]++ == 0) {
                ++d;
            }   
            while(d > k) {
                if(--freq[nums[i]] == 0) {
                    --d;
                }
                ++i;
            }
            res += j - i + 1;
        }
        while(i < n) {
            --freq[nums[i]];
            ++i;
        }
        return res;
    }
public:
    int medianOfUniquenessArray(vector<int>& nums) {
        const int n = nums.size();
        long long subs = 1ll * n * (n + 1) / 2;
        long long median = (subs - 1) / 2 + 1;
        int l = 1, r = n;
        while(l < r) {
            int m = (l + r) / 2;
            if(check(nums, m) < median) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        return l;
    }
};