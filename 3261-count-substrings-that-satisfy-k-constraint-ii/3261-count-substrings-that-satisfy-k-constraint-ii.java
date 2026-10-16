class Solution {
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length(), m = queries.length;
        
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = s.charAt(i) - '0';
        }
        
        long pre = 0;
        long[] presum = new long[n];
        int[] thresholdEndWith = new int[n];
        int[] runningCounts = new int[2];
        for (int i = 0, j = 0; i < n; i++) {
            while (j < n) {
                runningCounts[data[j]]++;
                if (runningCounts[0] <= k || runningCounts[1] <= k) {
                    thresholdEndWith[j] = i;
                    j++;
                } else {
                    runningCounts[data[j]]--;
                    break;
                }
            }
            pre = presum[i] = pre + (j - i);
            runningCounts[data[i]]--;
        }
        
        long[] ans = new long[m];
        for (int i = 0; i < m; i++) {
            int[] q = queries[i];
            int th = Math.max(q[0], thresholdEndWith[q[1]]);
            if (q[0] < th) {
                // left (pink-background) part, by presum approach
                ans[i] = presum[th - 1] - (q[0] == 0 ? 0 : presum[q[0] - 1]);
            }
            // right (blue-background) part, by mathematical fomula
            ans[i] += (long)(q[1] - th + 2) * (q[1] - th + 1) / 2;
        }
        return ans;
    }
}