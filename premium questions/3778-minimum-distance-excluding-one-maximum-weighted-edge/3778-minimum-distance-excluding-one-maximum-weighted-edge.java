class Solution {
    public long minCostExcludingMax(int n, int[][] edges) {
        Arrays.sort(edges, (a, b) -> a[0] - b[0]); //To avoid the worst case.
        long[] steps1 = new long[n];
        long[] steps2 = new long[n];
        long max = Long.MAX_VALUE / 2;
        Arrays.fill(steps1, max);
        Arrays.fill(steps2, max);
        steps1[0] = 0;
        steps2[0] = 0;
        boolean changed = true;
        while (changed) {
            changed = false;
            for (int i = 0; i < edges.length; i++) {
                int a = edges[i][0];
                int b = edges[i][1];
                int stp = edges[i][2];
                if (steps1[b] > steps1[a] + stp) {
                    changed = true;
                    steps1[b] = steps1[a] + stp;
                } else if (steps1[a] > steps1[b] + stp) {
                    changed = true;
                    steps1[a] = steps1[b] + stp;
                }
                if (steps2[b] > steps1[a]) {
                    changed = true;
                    steps2[b] = steps1[a];
                } else if (steps2[a] > steps1[b]) {
                    changed = true;
                    steps2[a] = steps1[b];
                }
                if (steps2[b] > steps2[a] + stp) {
                    changed = true;
                    steps2[b] = steps2[a] + stp;
                } else if (steps2[a] > steps2[b] + stp) {
                    changed = true;
                    steps2[a] = steps2[b] + stp;
                }
            }
            for (int i = edges.length - 1; i >= 0; i--) {
                int a = edges[i][0];
                int b = edges[i][1];
                int stp = edges[i][2];
                if (steps1[b] > steps1[a] + stp) {
                    changed = true;
                    steps1[b] = steps1[a] + stp;
                } else if (steps1[a] > steps1[b] + stp) {
                    changed = true;
                    steps1[a] = steps1[b] + stp;
                }
                if (steps2[b] > steps1[a]) {
                    changed = true;
                    steps2[b] = steps1[a];
                } else if (steps2[a] > steps1[b]) {
                    changed = true;
                    steps2[a] = steps1[b];
                }
                if (steps2[b] > steps2[a] + stp) {
                    changed = true;
                    steps2[b] = steps2[a] + stp;
                } else if (steps2[a] > steps2[b] + stp) {
                    changed = true;
                    steps2[a] = steps2[b] + stp;
                }
            }
        }
        return steps2[n - 1];
    }
}