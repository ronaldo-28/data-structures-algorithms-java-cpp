class Solution {
public:
    int sumCounts(vector<int>& nums) {
        int count[101], n = nums.size(), ans = 0;
        for (int i = 0; i < n; ++i) {
            fill_n(count, 101, 0);
            int uniq = 0;
            for (int j = i; j < n; ++j) {
                uniq += 0 == count[nums[j]]++;
                ans += uniq * uniq;
            }
        }
        return ans;
    }
};