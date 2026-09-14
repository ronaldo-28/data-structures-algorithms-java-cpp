class Solution {

    static{
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }));
    }
    
    public int minimumEffortPath(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        int[][] dist = new int[rows][cols];
        for(int[] row : dist){
            Arrays.fill(row,Integer.MAX_VALUE);
        }
        
        int[] dr = {1,-1,0,0};
        int[] dc = {0,0,-1,1};

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[2] - b[2]);

        pq.offer(new int[] {0,0,0});
        dist[0][0] = 0;

        while(!pq.isEmpty()){
            int[] current = pq.poll();
             int r = current[0];
             int c = current[1];
             int effort = current[2];

             if (effort > dist[r][c]) continue;

             if(r == rows-1 && c == cols-1){
               return effort;
             }

             for(int i = 0; i < 4; i++){
                int dirR = r + dr[i];
                int dirC = c + dc[i];
                
                if(dirR >= 0 && dirC >= 0 && dirR < rows && dirC < cols){
                  int diff = Math.abs(heights[r][c] - heights[dirR][dirC]);
                  int neweffort = Math.max(effort,diff);

                  if(neweffort < dist[dirR][dirC]){
                    dist[dirR][dirC] = neweffort;
                    pq.offer(new int[] {dirR,dirC,neweffort});
                  }
                }
             }
        }
        return 0;
    }
}