class Solution {
    public int maxTotalReward(int[] reward) {
        int n = reward.length;
        Arrays.sort(reward);
        int answer = reward[n - 1];

        int[] cache = new int[reward[n - 1] + 1];
        Arrays.fill(cache, -1);

        return reward[n - 1] + run(reward, reward[n - 1], cache);
    }

    private int run(int[] arr, int limit, int[] cache) {
        if (cache[limit] != -1) {
            return cache[limit];
        }
        int idx = lowerBoundIdx(arr, limit);
        if (idx == -1) {
            cache[limit] = 0;
            return 0;
        }
        if (arr[idx] == limit - 1) {
            cache[limit] = arr[idx];
            return arr[idx];
        }
        int answer = 0;
        for (int i = idx; i >= 0; i--) {
            answer = Math.max(answer, arr[i] + run(arr, Math.min(limit - arr[i], arr[i]), cache));
        }
        cache[limit] = answer;
        return answer;
    }

    private int lowerBoundIdx(int[] arr, int k) {
        int l = -1;
        int r = arr.length - 1;
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (arr[m] < k) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}