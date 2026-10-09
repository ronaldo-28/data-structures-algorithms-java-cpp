class Solution {
    public int minimumArea(int[][] arr) {
        int rows = arr.length , cols = arr[0].length;
        int ri = 0 , rl= 0 , ci=0 , cl=0;
        int flag = 0;
        // check ri
        for(int i=0; i<rows; i++){
            for(int j= 0; j<cols; j++){
                if(arr[i][j] == 1){
                    ri = i;
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) break;
        }
        flag = 0;
        // check rL
        for(int i=rows-1; i>=0; i--){
            for(int j= 0; j<cols; j++){
                if(arr[i][j] == 1){
                    rl = i;
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) break;
        }
        flag = 0;
        //check ci
        for(int i = 0; i < cols; i++){
            for(int j= 0; j<rows; j++){
                if(arr[j][i] == 1){
                    ci = i;
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)break;
        }
        flag = 0;
        // check cl
        for(int i = cols-1; i >= 0; i--){
            for(int j = 0; j<rows; j++){
                if(arr[j][i] == 1){
                    cl = i;
                    flag = 1;
                    break;
                }
            }
            if(flag == 1) break;
        }
        return (cl-ci+1)*(rl-ri+1);
        
    }
}