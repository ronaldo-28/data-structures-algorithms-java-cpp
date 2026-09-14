class Solution {
    public int findMinimumTime(int[] strength) {
        int n = strength.length;
        int[][] cost = new int[n + 1][n + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) cost[i + 1][j + 1] = (strength[i] + j) / (j + 1);
        }
        return hungarian(cost);
    }
    private int hungarian(int[][] cost) {
        int n = cost.length, m = cost[0].length;
        int[] u = new int[n], v = new int[m], p = new int[m], way = new int[m];
        for(int i = 1; i < n; i++) {
            p[0] = i;
            int j0 = 0;
            int[] min = new int[m];
            boolean[] seen = new boolean[m];
            Arrays.fill(min, Integer.MAX_VALUE);
            while(p[j0] != 0) {
                seen[j0] = true;
                int i0 = p[j0], current = Integer.MAX_VALUE, minCol = 0;
                for(int j = 1; j < m; j++) {
                    if(!seen[j]) {
                        int val = cost[i0][j] - u[i0] - v[j];
                        if(val < min[j]) {
                            min[j] = val;
                            way[j] = j0;
                        }
                        if(min[j] < current) {
                            current = min[j];
                            minCol = j;
                        }
                    }
                }
                for(int j = 0; j < m; j++) {
                    if(seen[j]) {
                        u[p[j]] += current;
                        v[j] -= current;
                    }else min[j] -= current;
                }
                j0 = minCol;
            }
            int temp = way[j0];
            p[j0] = p[temp];
            j0 = temp;
            while(j0 != 0) {
                temp = way[j0];
                p[j0] = p[temp];
                j0 = temp;
            }
        }
        return -v[0];
    }
}