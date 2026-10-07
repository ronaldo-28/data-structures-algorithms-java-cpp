class Solution {

    int[] rewardValues;
    int length;
    Integer[] memo;

    public int maxTotalReward(int[] values) {
        Arrays.sort(values);

        int n = 0;

        for (int x : values) {
            if (n == 0 || values[n - 1] != x) {
                values[n++] = x;
            }
        }

        rewardValues = values;
        length = n;

        memo = new Integer[4001];

        return dfs(0);
    }

    private int dfs(int currentReward) {

        if (memo[currentReward] != null) {
            return memo[currentReward];
        }

        int best = currentReward;

        int start = upperBound(currentReward);

        for (int i = start; i < length; i++) {
            best = Math.max(
                best,
                dfs(currentReward + rewardValues[i])
            );
        }

        return memo[currentReward] = best;
    }

    private int upperBound(int target) {
        int left = 0;
        int right = length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (rewardValues[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}