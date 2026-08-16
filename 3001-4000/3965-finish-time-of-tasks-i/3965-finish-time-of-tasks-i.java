class Solution {
    private long dfs(int x, List<List<Integer>> con, int[] baseTime) {
        long r = baseTime[x];

        if (!con.get(x).isEmpty()) {
            long earliest = Long.MAX_VALUE;
            long latest = Long.MIN_VALUE;

            for (int y : con.get(x)) {
                long temp = dfs(y, con, baseTime);
                earliest = Math.min(earliest, temp);
                latest = Math.max(latest, temp);
            }

            r += latest - earliest + latest;
        }

        return r;
    }

    public long finishTime(int n, int[][] edges, int[] baseTime) {
        List<List<Integer>> con = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            con.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            con.get(e[0]).add(e[1]);
        }

        return dfs(0, con, baseTime);
    }
}