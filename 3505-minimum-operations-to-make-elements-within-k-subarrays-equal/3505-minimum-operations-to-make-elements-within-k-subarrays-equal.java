class Solution {
    int size;
    long[] cntTree;
    long[] sumTree;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        for (int i = 0; i < n; i++) {
            if (m == 0 || sorted[m - 1] != sorted[i]) {
                sorted[m++] = sorted[i];
            }
        }
        int[] vals = Arrays.copyOf(sorted, m);
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }
        size = m;
        cntTree = new long[m + 1];
        sumTree = new long[m + 1];
        int windows = n - x + 1;
        long[] cost = new long[windows];
        int half = (x + 1) / 2;
        long total = 0;
        for (int i = 0; i < n; i++) {
            add(rank[i], 1, nums[i]);
            total += nums[i];
            if (i >= x) {
                add(rank[i - x], -1, -nums[i - x]);
                total -= nums[i - x];
            }
            if (i >= x - 1) {
                int pos = 0;
                long cntBelow = 0;
                long sumBelow = 0;
                int step = Integer.highestOneBit(m);
                while (step > 0) {
                    int nxt = pos + step;
                    if (nxt <= m && cntBelow + cntTree[nxt] < half) {
                        pos = nxt;
                        cntBelow += cntTree[nxt];
                        sumBelow += sumTree[nxt];
                    }
                    step >>= 1;
                }
                long med = vals[pos];
                long leftCnt = half;
                long leftSum = sumBelow + (half - cntBelow) * med;
                long rightCnt = x - leftCnt;
                long rightSum = total - leftSum;
                cost[i - x + 1] = med * leftCnt - leftSum + rightSum - med * rightCnt;
            }
        }
        long inf = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        long[] cur = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            Arrays.fill(cur, 0, n + 1, inf);
            for (int i = j * x; i <= n; i++) {
                long take = (j == 1 ? 0 : prev[i - x]) + cost[i - x];
                cur[i] = Math.min(cur[i - 1], take);
            }
            long[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        return prev[n];
    }

    void add(int i, int c, long v) {
        for (; i <= size; i += i & -i) {
            cntTree[i] += c;
            sumTree[i] += v;
        }
    }
}