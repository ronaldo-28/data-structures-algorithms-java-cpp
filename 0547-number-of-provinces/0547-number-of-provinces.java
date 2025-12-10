class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length; // total number of cities
        boolean[] visited = new boolean[n];
        int res = 0;

        for(int i =0;i<n;i++){
            if(!visited[i]){
                dfs(isConnected, visited, i);
                res++;
            }
        }
        return res;
    }
    public void dfs (int[][] isConnected, boolean[] visited, int i){
        visited[i] = true;

        for(int j =0;j<isConnected.length;j++){
            if(isConnected[i][j] == 1 && !visited[j]){
                dfs(isConnected, visited, j);
            }
        }
    }
}