class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] degree = new int[n];
        for(List<Integer> edge : edges) {
            degree[edge.get(0) - 1]++;
            degree[edge.get(1) - 1]++;
        }
        int count = 0;
        for(int i = 0; i < n; i++) count += degree[i] & 1;
        System.out.println(count);
        
        if(count == 0) return true;
        if(count > 4 || (count & 1) == 1) return false;

        if(count == 4) {
            int index = 0;
            int[] id = new int[n];
            for(int i = 0; i < n; i++) {
                if((degree[i] & 1) == 1) id[i] = index++;
                else id[i] = -1;
            }

            boolean[][] adj = new boolean[4][4];

            for(List<Integer> edge : edges) {
                int a = id[edge.get(0) - 1], b = id[edge.get(1) - 1];
                if(a == -1 || b == -1) continue;
                adj[a][b] = adj[b][a] = true;
            }
            int a = 0, b = 1, c = 2, d = 3;
            return !adj[a][b] && !adj[c][d] || !adj[a][c] && !adj[b][d] || !adj[a][d] && !adj[b][c];
        }
        if(count != 2) return false;

        int index = 0;
        int[] id = new int[n];
        for(int i = 0; i < n; i++) {
            if((degree[i] & 1) == 1) id[i] = index++;
            else id[i] = -1;
        }

        boolean[][] adj = new boolean[2][n];

        boolean flag = false;
        for(List<Integer> edge : edges) {
            int a = edge.get(0) - 1, b = edge.get(1) - 1;
            if(id[a] != -1 && id[b] != -1) flag = true;

            if(id[a] != -1) adj[id[a]][b] = true;
            if(id[b] != -1) adj[id[b]][a] = true;
        }
        if(!flag) return true;
        
        for(int i = 0; i < n; i++) {
            if(id[i] == -1 && !adj[0][i] && !adj[1][i]) return true;
        }
        return false;
    }
}