import java.util.Arrays;

class Solution {
    private record Query(int l, int r, int threshold, int id) {}

    public int[] subarrayMajority(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        int blockSize = Math.max(1, (int) Math.sqrt(n));

        // 1. Coordinate Compression: map values to [0, m)
        int[] uniqueVals = nums.clone();
        Arrays.sort(uniqueVals);
        int m = 1;
        for (int i = 1; i < n; i++) {
            if (uniqueVals[i] != uniqueVals[i - 1]) {
                uniqueVals[m++] = uniqueVals[i];
            }
        }
        int[] compressed = new int[n];
        for (int i = 0; i < n; i++) {
            compressed[i] = Arrays.binarySearch(uniqueVals, 0, m, nums[i]);
        }

        // 2. Sort queries by (left block ascending, right endpoint ascending)
        Query[] sortedQueries = new Query[q];
        for (int i = 0; i < q; i++) {
            sortedQueries[i] = new Query(queries[i][0], queries[i][1], queries[i][2], i);
        }
        Arrays.sort(sortedQueries, (a, b) -> {
            int blockA = a.l / blockSize;
            int blockB = b.l / blockSize;
            return blockA != blockB ? Integer.compare(blockA, blockB) : Integer.compare(a.r, b.r);
        });

        int[] ans = new int[q];
        int[] cnt = new int[m];

        // 3. Process queries block by block
        for (int BL = 0, j = 0; BL < n && j < q; BL += blockSize) {
            int BR = Math.min(BL + blockSize - 1, n - 1);
            Arrays.fill(cnt, 0);

            int maxCnt = 0;
            int minVal = m;
            int L = BR;
            int R = BR - 1;

            // Process all queries whose left endpoint falls in [BL, BR]
            while (j < q && sortedQueries[j].l <= BR) {
                Query cur = sortedQueries[j];
                int l = cur.l;
                int r = cur.r;

                if (r < BR) {
                    // Case A: Query is completely inside the current block -> brute force
                    for (int i = l; i <= r; i++) {
                        int v = compressed[i];
                        cnt[v]++;
                        if (cnt[v] > maxCnt || (cnt[v] == maxCnt && v < minVal)) {
                            maxCnt = cnt[v];
                            minVal = v;
                        }
                    }
                    ans[cur.id] = maxCnt >= cur.threshold ? uniqueVals[minVal] : -1;

                    // Rollback brute force counts
                    for (int i = l; i <= r; i++) {
                        cnt[compressed[i]]--;
                    }
                    maxCnt = 0;
                    minVal = m;
                } else {
                    // Case B: Query spans across blocks
                    // Step 1: Expand R monotonically to r
                    while (R < r) {
                        int v = compressed[++R];
                        cnt[v]++;
                        if (cnt[v] > maxCnt || (cnt[v] == maxCnt && v < minVal)) {
                            maxCnt = cnt[v];
                            minVal = v;
                        }
                    }

                    // Step 2: Snapshot the state for [BR, R]
                    int savedMaxCnt = maxCnt;
                    int savedMinVal = minVal;

                    // Step 3: Temporarily expand L leftward from BR to l
                    while (L > l) {
                        int v = compressed[--L];
                        cnt[v]++;
                        if (cnt[v] > maxCnt || (cnt[v] == maxCnt && v < minVal)) {
                            maxCnt = cnt[v];
                            minVal = v;
                        }
                    }

                    // Step 4: Record the answer for this query
                    ans[cur.id] = maxCnt >= cur.threshold ? uniqueVals[minVal] : -1;

                    // Step 5: Rollback L back to BR and restore snapshot
                    while (L < BR) {
                        cnt[compressed[L++]]--;
                    }
                    maxCnt = savedMaxCnt;
                    minVal = savedMinVal;
                }
                j++;
            }
        }

        return ans;
    }
}