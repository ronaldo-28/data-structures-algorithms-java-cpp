class Solution {
    static final int INF = Integer.MAX_VALUE / 2;

    public int containVirus(int[][] isInfected) {
        return new Solver(isInfected).solve();
    }

    static final class Solver {
        final int row;
        final int[] g;
        static final int[] q = new int[5000];
        static final int[] nodes = new int[5000];
        int nodesStart = 0;
        int nodesEnd = 0;
        int qlen = 0;
        int walls;
        int threats;
        int counter = 2;
        int oldMarker;

        Solver(int[][] infected) {
            final int m = infected.length;
            final int n = infected[0].length;
            final int m2 = m + 2;
            this.row = n + 1;
            final int total = m2 * row;
            this.g = new int[total];
            Arrays.fill(g, 0, row, INF);
            Arrays.fill(g, total - row, total, INF);
            int gi = row;
            for (final int[] ir : infected) {
                for (int j = 0; j < n; j++) {
                    final int v = ir[j];
                    if (v > 0) {
                        nodes[nodesEnd++] = gi;
                    }
                    g[gi++] = v;
                }
                g[gi++] = INF;
            }
        }

        int solve() {
            //System.out.println("????\n" + this);
            int r = 0;
            while (true) {
                final int spread = spread();
                if (spread == 0) {
                    return r;
                }
                r += spread;
            }
        }

        int spread() {
            final int nodesEnd = this.nodesEnd;
            int w = 0;
            int t = 0;
            int wallCounter = -1;
            int wStart = -1;
            int wEnd = -1;
            int qStart = -1;
            int qEnd = -1;
            oldMarker = ++counter;
            for (int nodeIdx = this.nodesStart; nodeIdx < nodesEnd; nodeIdx++) {
                final int i = nodes[nodeIdx];
                final int v = g[i];
                if (v > 0 && v < oldMarker) {
                    final int nstart = this.nodesEnd;
                    final int qstart = this.qlen;
                    check(i);
                    if (threats > t) {
                        t = threats;
                        w = walls;
                        wallCounter = counter;
                        wStart = nstart;
                        wEnd = this.nodesEnd;
                        qStart = qstart;
                        qEnd = qlen;
                    }
                }
            }
            //System.out.println("Before fix:\n" + this);
            for (int i = wStart; i < wEnd; i++) {
                final int node = nodes[i];
                if (g[node] == wallCounter) {
                    g[node] = 0;
                }
            }
            //System.out.println("After fix:\n" + this);
            for (int i = qStart; i < qEnd; i++) {
                g[q[i]] = INF;
            }
            this.nodesStart = nodesEnd;
            //System.out.println("After walling:\n" + this);
            return w;
        }

        void check(int start) {
            final int qstart = qlen;
            threats = 0;
            walls = 0;
            counter++;
            maybeCheck(start);
            for (int i = qstart; i < qlen; i++) {
                processCheck(q[i]);
            }
        }

        void processCheck(int node) {
            maybeCheck(node + 1);
            maybeCheck(node - 1);
            maybeCheck(node + row);
            maybeCheck(node - row);
        }

        void maybeCheck(int node) {
            final int v = g[node];
            if (v != INF) {
                if (v > oldMarker || v == 0) {
                    walls++;
                    if (v != counter) {
                        threats++;
                        g[node] = counter;
                        if (v == 0) {
                            nodes[nodesEnd++] = node;
                        }
                    }
                } else if (v < oldMarker) {
                    g[node] = oldMarker;
                    q[qlen++] = node;
                }
            }
        }

        @Override
        public String toString() {
            final char[] arr = new char[g.length - 2 * row];
            for (int i = 0; i < arr.length; i++) {
                final int v = g[i + row];
                arr[i] = i % row == row - 1 ? '\n' : v <= 0 ? ' ' : v == INF ? 'W' : '*';
            }
            return new String(arr);
        }
    }
}