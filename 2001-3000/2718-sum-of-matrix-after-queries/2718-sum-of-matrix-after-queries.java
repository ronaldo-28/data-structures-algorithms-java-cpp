class Solution {
    public long matrixSumQueries(int n, int[][] queries) {
        boolean[] rowVisited = new boolean[n];
        boolean[] colVisited = new boolean[n];

        int rowsDone = 0;
        int colsDone = 0;
        long ans = 0;

        for (int i = queries.length - 1; i >= 0; i--) {
            int type = queries[i][0];
            int index = queries[i][1];
            int val = queries[i][2];

            if (type == 0) {
                if (rowVisited[index]) continue;

                ans += (long) val * (n - colsDone);
                rowVisited[index] = true;
                rowsDone++;
            } else {
                if (colVisited[index]) continue;

                ans += (long) val * (n - rowsDone);
                colVisited[index] = true;
                colsDone++;
            }
        }

        return ans;
    }
}