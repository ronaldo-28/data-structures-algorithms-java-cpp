class Solution {
    public int minChanges(int[] nums, int k) {
        int n = nums.length;
        int[] diff = new int[k + 2];

        for (int i = 0; i < n / 2; i++) {
            int x = Math.min(nums[i], nums[n - i - 1]);
            int y = Math.max(nums[i], nums[n - i - 1]);

            int current = y - x;
            int oneChange = Math.max(y, k - x);

            diff[0] += 1;

            diff[current] -= 1;
            diff[current + 1] += 1;

            diff[oneChange + 1] -= 1;
            diff[oneChange + 1] += 2;
        }

        int answer = n;
        int changes = 0;

        for (int d : diff) {
            changes += d;
            answer = Math.min(answer, changes);
        }

        return answer;
    }
}