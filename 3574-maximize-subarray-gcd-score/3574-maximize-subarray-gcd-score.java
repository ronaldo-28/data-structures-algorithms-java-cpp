class Solution {
    public long maxGCDScore(int[] nums, int k) {
        // Step 1: Count factors of 2 for each element.
        int n = nums.length;
        int[] cnt = new int[n];

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (x % 2 == 0) {
                cnt[i]++;
                x /= 2;
            }
        }

        // Step 2: Enumerate all contiguous subarrays.
        long ans = 0;

        for (int l = 0; l < n; l++) {
            int g = 0, minimum = Integer.MAX_VALUE, count = 0;

            for (int r = l; r < n; r++) {
                // Step 3: Update GCD and minimum factor count.
                g = gcd(g, nums[r]);

                if (cnt[r] < minimum) {
                    minimum = cnt[r];
                    count = 1;
                } else if (cnt[r] == minimum) {
                    count++;
                }

                // Step 4: Double the GCD when possible.
                long score = (long)(r - l + 1) * g;
                if (count <= k) score *= 2;

                ans = Math.max(ans, score);
            }
        }

        // Step 5: Return the maximum score.
        return ans;
    }

    private int gcd(int a, int b) {
        // Step 1: Calculate GCD using Euclid's algorithm.
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}