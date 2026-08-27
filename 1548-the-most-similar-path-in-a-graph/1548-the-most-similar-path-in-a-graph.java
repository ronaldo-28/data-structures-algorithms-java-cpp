class Solution {
    public List<Integer> mostSimilar(int n, int[][] roads, String[] names, String[] targetPath) {
        boolean[][] graph = new boolean[n][n];
        for (int[] r : roads) {
            graph[r[0]][r[1]] = true;
            graph[r[1]][r[0]] = true;
        }
        
        int m = targetPath.length;
        int[][] dp = new int[m][n];
        int[][] prev = new int[m][n];
        
        for (int j = 0; j < n; j++) dp[0][j] = names[j].equals(targetPath[0]) ? 0 : 1;
        
        // i,j loop to fill dp matrix
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int min = m + 1;
                // k loop to find connection between prev -> cur
                for (int k = 0; k < n; k++) {
                    if (graph[k][j]) {
                        if (dp[i-1][k] < min) {
                            min = dp[i-1][k];
                            prev[i][j] = k;
                        }
                    }
                }
                
                dp[i][j] = min + ( names[j].equals(targetPath[i]) ? 0 : 1 );
            }
        }
        
        List<Integer> res = new ArrayList<>();
        
        // From the last row, find the min Edit distance and pick a random valid end node
        int min = m + 1, candidate = 0;
        for (int j = 0; j < n; j++) {
            if (dp[m-1][j] < min) {
                min = dp[m-1][j];
                candidate = j;
            }
        }
        
        res.add(candidate);
        for (int i = m-1; i > 0; i--) {
            candidate = prev[i][candidate];
            res.add(candidate);
        }
            
        Collections.reverse(res);
        return res;
    }
}