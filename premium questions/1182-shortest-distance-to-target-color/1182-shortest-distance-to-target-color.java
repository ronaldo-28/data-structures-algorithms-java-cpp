class Solution {
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        // initializations
        int n = colors.length;
        int[] rightmost = {0, 0, 0};
        int[] leftmost = {n - 1, n - 1, n - 1};

        int[][] distance = new int[3][n];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                distance[i][j] = -1;
            }
        }

        // looking forward
        for (int i = 0; i < n; i++) {
            int color = colors[i] - 1;
            for (int j = rightmost[color]; j < i + 1; j++) {
                distance[color][j] = i - j;
            }
            rightmost[color] = i + 1;
        }

        // looking backward
        for (int i = n - 1; i > -1; i--) {
            int color = colors[i] - 1;
            for (int j = leftmost[color]; j > i - 1; j--) {
                if (distance[color][j] == -1 || distance[color][j] > j - i) {
                    distance[color][j] = j - i;
                }
            }
            leftmost[color] = i - 1;
        }

        List<Integer> queryResults = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            queryResults.add(distance[queries[i][1] - 1][queries[i][0]]);
        }
        return queryResults;

    }
}