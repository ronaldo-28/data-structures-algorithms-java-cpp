class Solution {

    static final int MOUSE_TURN = 0, CAT_TURN = 1;
    static final int DRAW = 0, MOUSE_WIN = 1, CAT_WIN = 2;    
    public int catMouseGame(int[][] graph) {
        int n = graph.length;

        int[][][] degrees = new int[n][n][2];

        for (int mousePos = 0; mousePos < n; mousePos++) {
            for (int catPos = 1; catPos < n; catPos++) {
                degrees[mousePos][catPos][0] = graph[mousePos].length;
                degrees[mousePos][catPos][1] = graph[catPos].length;
            }
        }
        
        for (int catPos : graph[0]) {
            for (int mousePos = 0; mousePos < n; mousePos++)
                degrees[mousePos][catPos][1]--;
        }
        
        int[][][] dp = new int[n][n][2];
        Queue<int[]> que = new ArrayDeque<>();
        
        for(int catPos = 1; catPos < n; catPos++){
            for(int turn = 0; turn <= 1; turn++){
                dp[0][catPos][turn] = 1;
                que.offer(new int[]{0, catPos, turn, 1});
                
                dp[catPos][catPos][turn] = 2;
                que.offer(new int[]{catPos, catPos, turn, 2});
            }
        }
        
        while(!que.isEmpty()){
            int[] cur = que.poll();
            int mousePos = cur[0], catPos = cur[1], turn = cur[2], result = cur[3];
            
            if(turn == 0){
                if(mousePos == 1 && catPos == 2)
                    return result;
                    
                for(int v : graph[catPos]){
                    if(v == 0 || dp[mousePos][v][1] != 0)
                        continue;
                   
                    if(result == 2 || --degrees[mousePos][v][1] == 0){
                        dp[mousePos][v][1] = result;
                        que.offer(new int[]{mousePos, v, 1, result});
                    }
                }
            } else {
                for(int v : graph[mousePos]){
                    if(dp[v][catPos][0] != 0)
                        continue;
                        
                    if(result == 1 || --degrees[v][catPos][0] == 0){
                        dp[v][catPos][0] = result;
                        que.offer(new int[]{v, catPos, 0, result});
                    }
                }
            }
        }

        return dp[1][2][0];        
    }
}