/* ------- Translated from Python code by ChatGPT ---------*/

class Solution {
    public int minArraySum(int[] nums, int k, int op1, int op2) {
        int n = nums.length;
        Arrays.sort(nums);

        // Find the boundaries for the three sections
        int m1 = lowerBound(nums, k);
        int m2 = lowerBound(nums, 2 * k - 1);

        Set<Integer> candidates = new HashSet<>();
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
                candidates.add(j);
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
        List<int[]> arr = new ArrayList<>();
        for (int idx = 0; idx < j; idx++) {
            arr.add(new int[]{nums[idx], idx});
        }
        arr.sort((a, b) -> Integer.compare(a[0], b[0])); // Sort by value descending

        while (op1 > 0 && !arr.isEmpty()) {
            int[] numIdx = arr.remove(arr.size() - 1);
            int num = numIdx[0], idx = numIdx[1];
            nums[idx] = (num + 1) / 2;
            op1--;

            if (candidates.contains(idx) && swapCnt > 0) {
                // Handle the swap case
                swapCnt--;
                nums[idx] -= 1;
            }
        }

        // Calculate the sum of the modified nums
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    // Helper function to find the lower bound
    private int lowerBound(int[] nums, int target) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}