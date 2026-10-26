/* ------- Translated from Python code by ChatGPT ---------*/

class Solution {
public:
    int minArraySum(vector<int>& nums, int k, int op1, int op2) {
        int n = nums.size();
        sort(nums.begin(), nums.end());

        // Find the boundaries for the three sections
        auto m1 = lower_bound(nums.begin(), nums.end(), k) - nums.begin();
        auto m2 = lower_bound(nums.begin(), nums.end(), 2 * k - 1) - nums.begin();

        set<int> candidates; // To track indices of even numbers in the middle section
        int swapCnt = 0;

        // Phase 1
        // Largest numbers, apply op1 then op2
        int i = n - 1;
        while (i >= m2 && op1 > 0) {
            nums[i] = (nums[i] + 1) / 2;
            op1--;
            if (op2 > 0) {
                nums[i] -= k;
                op2--;
            }
            i--;
        }

        // Phase 2
        // Apply op2 in the middle section, from left to right
        int j = m1;
        while (j <= i && op2 > 0) {
            if (k % 2 == 1 && nums[j] % 2 == 0) {
                // k is odd and nums[j] is even, mark as a candidate for swapping
                candidates.insert(j);
            }
            nums[j] -= k;
            op2--;
            j++;
        }

        // Phase 3
        // Apply op1 to numbers in the middle section not already affected by op2
        while (i >= j && op1 > 0) {
            if (k % 2 == 1 && nums[i] % 2 == 1) {
                // k is odd and nums[i] is odd, increase swap count
                swapCnt++;
            }
            nums[i] = (nums[i] + 1) / 2;
            op1--;
            i--;
        }

        // Phase 4
        // Sort remaining untouched numbers and apply op1 greedily
        vector<pair<int, int>> arr;
        for (int idx = 0; idx < j; idx++) {
            arr.emplace_back(nums[idx], idx);
        }
        sort(arr.begin(), arr.end()); // Sort in descending order

        while (op1 > 0 && !arr.empty()) {
            auto [num, idx] = arr.back();
            arr.pop_back();
            nums[idx] = (num + 1) / 2;
            op1--;

            if (candidates.count(idx) && swapCnt > 0) {
                // Handle the swap case
                swapCnt--;
                nums[idx] -= 1;
            }
        }

        // Return the sum of the modified nums
        return accumulate(nums.begin(), nums.end(), 0);
    }
};