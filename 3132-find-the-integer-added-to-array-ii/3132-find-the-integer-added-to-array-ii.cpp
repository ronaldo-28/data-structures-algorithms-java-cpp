class Solution {
public:
    int minimumAddedInteger(vector<int>& nums1, vector<int>& nums2) {
        sort(nums1.begin(), nums1.end());
        sort(nums2.begin(), nums2.end());

        int n = nums1.size();
        int ans = INT_MAX;

        for (int i = 0; i < 3; i++) {

            int x = nums2[0] - nums1[i];

            int p1 = 0;
            int p2 = 0;
            int removed = 0;

            while (p1 < n && p2 < n - 2) {

                if (nums1[p1] + x == nums2[p2]) {
                    p1++;
                    p2++;
                }
                else {
                    p1++;
                    removed++;
                }

                if (removed > 2)
                    break;
            }

            // Exactly two elements of nums1 must be removed.
            removed += n - p1;

            if (p2 == n - 2 && removed == 2) {
                ans = min(ans, x);
            }
        }

        return ans;
    }
};