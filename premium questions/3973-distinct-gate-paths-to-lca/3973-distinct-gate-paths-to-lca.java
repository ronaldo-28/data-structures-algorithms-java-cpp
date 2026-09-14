class Solution {
    private static final long MOD = 1_000_000_007L;
    private int log;
    private int[] depth;
    private int[][] up;
    private long[][] matrix00;
    private long[][] matrix01;
    private long[][] matrix10;
    private long[][] matrix11;

    public int distinctPaths(int n, int[] parent, int[][] gates, int[][] queries) {
        log = 32 - Integer.numberOfLeadingZeros(n);
        depth = buildDepth(n, parent);
        buildLiftingTables(n, parent, gates);

        int res = Arrays.stream(queries)
                .mapToInt(query -> {
                    int ancestor = lca(query[0], query[2]);
                    long aliceWays = countWays(
                            query[0],
                            depth[ancestor],
                            query[1]);
                    long bobWays = countWays(
                            query[2],
                            depth[ancestor],
                            query[3]);

                    return (int) (aliceWays * bobWays % MOD);
                })
                .reduce(0, (left, right) -> left ^ right);

        return res;
    }

    private int[] buildDepth(int n, int[] parent) {
        int[] head = new int[n];
        int[] next = new int[n];
        int[] stack = new int[n];
        int[] res = new int[n];

        Arrays.fill(head, -1);
        Arrays.fill(next, -1);

        for (int node = 1; node < n; node++) {
            next[node] = head[parent[node]];
            head[parent[node]] = node;
        }

        int size = 0;
        stack[size++] = 0;

        while (size > 0) {
            int node = stack[--size];
            for (int child = head[node]; child != -1; child = next[child]) {
                res[child] = res[node] + 1;
                stack[size++] = child;
            }
        }

        return res;
    }

    private void buildLiftingTables(int n, int[] parent, int[][] gates) {
        up = new int[log][n];
        matrix00 = new long[log][n];
        matrix01 = new long[log][n];
        matrix10 = new long[log][n];
        matrix11 = new long[log][n];

        System.arraycopy(parent, 0, up[0], 0, n);

        for (int node = 0; node < n; node++) {
            matrix00[0][node] = gates[node][0];
            matrix01[0][node] = gates[node][2];
            matrix10[0][node] = gates[node][2];
            matrix11[0][node] = gates[node][1];
        }

        for (int level = 1; level < log; level++) {
            for (int node = 0; node < n; node++) {
                int middle = up[level - 1][node];

                if (middle == -1) {
                    up[level][node] = -1;
                    continue;
                }

                up[level][node] = up[level - 1][middle];

                if (up[level][node] == -1) {
                    continue;
                }

                long a00 = matrix00[level - 1][middle];
                long a01 = matrix01[level - 1][middle];
                long a10 = matrix10[level - 1][middle];
                long a11 = matrix11[level - 1][middle];

                long b00 = matrix00[level - 1][node];
                long b01 = matrix01[level - 1][node];
                long b10 = matrix10[level - 1][node];
                long b11 = matrix11[level - 1][node];

                matrix00[level][node] = (a00 * b00 + a01 * b10) % MOD;
                matrix01[level][node] = (a00 * b01 + a01 * b11) % MOD;
                matrix10[level][node] = (a10 * b00 + a11 * b10) % MOD;
                matrix11[level][node] = (a10 * b01 + a11 * b11) % MOD;
            }
        }
    }

    private int lca(int first, int second) {
        if (depth[first] < depth[second]) {
            int temporary = first;
            first = second;
            second = temporary;
        }

        int difference = depth[first] - depth[second];

        for (int level = 0; difference > 0; level++) {
            if ((difference & 1) != 0) {
                first = up[level][first];
            }
            difference >>= 1;
        }

        if (first == second) {
            return first;
        }

        for (int level = log - 1; level >= 0; level--) {
            if (up[level][first] != up[level][second]) {
                first = up[level][first];
                second = up[level][second];
            }
        }

        return up[0][first];
    }

    private long countWays(
            int node,
            int targetDepth,
            int startingCard) {
        long redWays = startingCard == 1 ? 1L : 0L;
        long blueWays = startingCard == 0 ? 1L : 0L;
        int steps = depth[node] - targetDepth;

        for (int level = 0; steps > 0; level++) {
            if ((steps & 1) != 0) {
                long nextRedWays = (matrix00[level][node] * redWays
                        + matrix01[level][node] * blueWays) % MOD;

                long nextBlueWays = (matrix10[level][node] * redWays
                        + matrix11[level][node] * blueWays) % MOD;

                redWays = nextRedWays;
                blueWays = nextBlueWays;
                node = up[level][node];
            }

            steps >>= 1;
        }

        long res = (redWays + blueWays) % MOD;
        return res;
    }
}