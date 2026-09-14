class Solution {
public:
    long long countPairs(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();

        vector<int> diff(n);

        for (int i = 0; i < n; i++) {
            diff[i] = nums1[i] - nums2[i];
        }

        sort(diff.begin(), diff.end());

        long long ans = 0;

        int left = 0;
        int right = n - 1;

        while (left < right) {

            if (diff[left] + diff[right] > 0) {
                // Every element from left to right-1
                // can pair with diff[right].
                ans += right - left;
                right--;
            } else {
                left++;
            }
        }

        return ans;
    }
};