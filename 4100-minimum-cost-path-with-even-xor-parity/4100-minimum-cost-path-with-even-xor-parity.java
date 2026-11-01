class Solution {

    void update(int v, int[] mini1, int[] mini2) {
        if (v <= mini1[0]) {
            mini2[0] = mini1[0];
            mini1[0] = v;
        } else if (v < mini2[0]) {
            mini2[0] = v;
        }
    }

    public int minActivationCost(int[][] edges, int[] values) {
        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.bitCount(values[i]) & 1;
        }

        int n = values.length;
        int r = Integer.MAX_VALUE;

        int[][] mini1 = new int[n][2];
        int[][] mini2 = new int[n][2];

        for (int i = 0; i < n; i++) {
            Arrays.fill(mini1[i], Integer.MAX_VALUE);
            Arrays.fill(mini2[i], Integer.MAX_VALUE);
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int cost = e[2];

            int type = values[v];
            if (cost <= mini1[u][type]) {
                mini2[u][type] = mini1[u][type];
                mini1[u][type] = cost;
            } else if (cost < mini2[u][type]) {
                mini2[u][type] = cost;
            }

            type = values[u];
            if (cost <= mini1[v][type]) {
                mini2[v][type] = mini1[v][type];
                mini1[v][type] = cost;
            } else if (cost < mini2[v][type]) {
                mini2[v][type] = cost;
            }
        }

        for (int i = 0; i < n; i++) {
            r = Math.min(r, mini1[i][values[i]]);

            if (values[i] == 1) {
                if (mini1[i][0] < Integer.MAX_VALUE &&
                    mini1[i][1] < Integer.MAX_VALUE) {

                    r = Math.min(
                        r,
                        mini1[i][0] + mini1[i][1]
                    );
                }
            } else {
                if (mini1[i][1] < Integer.MAX_VALUE &&
                    mini2[i][1] < Integer.MAX_VALUE) {

                    r = Math.min(
                        r,
                        mini1[i][1] + mini2[i][1]
                    );
                }
            }
        }

        return r < Integer.MAX_VALUE ? r : -1;
    }
}