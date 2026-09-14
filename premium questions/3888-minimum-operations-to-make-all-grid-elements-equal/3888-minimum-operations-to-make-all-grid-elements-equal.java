class Solution {
    public long minOperations(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        long[] colSum = new long[n];
        long[][] opsRing = new long[k][n];

        boolean hasCandidate = false;
        long candidateX = 0;

        long sumOps0 = 0;
        long sumOps1 = 0;
        long minX = Long.MIN_VALUE;

        for (int i = 0; i < m; i++) {

            if (i >= k) {
                int evictRow = i % k;

                for (int j = 0; j < n; j++) {
                    colSum[j] -= opsRing[evictRow][j];
                    opsRing[evictRow][j] = 0;
                }
            }

            long windowSum = 0;

            for (int j = 0; j < n; j++) {
                windowSum += colSum[j];

                long req0 = -((long) grid[i][j]) - windowSum;

                if (i <= m - k && j <= n - k) {

                    opsRing[i % k][j] = req0;
                    colSum[j] += req0;
                    windowSum += req0;

                    long o1 = (i % k == 0 && j % k == 0) ? 1 : 0;

                    sumOps0 += req0;
                    sumOps1 += o1;

                    if (o1 == 1) {
                        if (-req0 > minX) {
                            minX = -req0;
                        }
                    } else {
                        if (req0 < 0) {
                            return -1;
                        }
                    }

                } else {

                    long r0 = req0;

                    long r1 = (((i / k) * k > m - k)
                            || ((j / k) * k > n - k)) ? 1 : 0;

                    if (r1 != 0) {
                        long x = -r0;

                        if (!hasCandidate) {
                            candidateX = x;
                            hasCandidate = true;
                        } else if (candidateX != x) {
                            return -1;
                        }

                    } else {
                        if (r0 != 0) {
                            return -1;
                        }
                    }
                }

                if (j >= k - 1) {
                    windowSum -= colSum[j - k + 1];
                }
            }
        }

        if (hasCandidate) {
            if (candidateX < minX) {
                return -1;
            }
        } else {
            candidateX = minX;
        }

        return sumOps0 + candidateX * sumOps1;
    }
}
