class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int rows = isWater.length;
        int cols = isWater[0].length;
        int INF = rows + cols;

        //Prepopulate
        for(int i =0; i < rows; i++){
            for(int j=0; j < cols; j++){
                if(isWater[i][j] == 1){
                    isWater[i][j] = 0;
                }else{
                    isWater[i][j] = INF;
                }
            }
        }

        //First Pass
        for(int i =0; i < rows; i++){
            for(int j=0; j < cols;j++){
                if(isWater[i][j] == 0) continue;
                int top = INF;
                int left = INF;
                if(i > 0) top = isWater[i-1][j];
                if(j > 0) left = isWater[i][j-1];
                isWater[i][j] = Math.min(top, left)+1;

            }
        }


        //Second Pass
        for(int i = rows - 1; i >= 0; i--){
            for(int j = cols - 1; j >= 0; j--){
                if(isWater[i][j] == 0) continue;
                int bottom = INF;
                int right = INF;
                if(i < rows - 1) bottom = isWater[i+1][j];
                if(j < cols - 1) right = isWater[i][j+1];
                isWater[i][j] = Math.min(isWater[i][j], Math.min(bottom, right) + 1);
            }
        }


        return isWater;
    }
}