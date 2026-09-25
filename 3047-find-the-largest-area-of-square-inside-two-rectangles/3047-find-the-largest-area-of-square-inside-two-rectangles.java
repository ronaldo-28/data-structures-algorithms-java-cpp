class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length ;
        
        int maxSide = 0 ;

        for(int i = 0 ; i < n ; i++ ){
            for(int j = i+1 ; j < n ;j++ ){
                int topRight_x = Math.min(topRight[i][0] , topRight[j][0] ) ;
                int topRight_y = Math.min(topRight[i][1] , topRight[j][1] ) ;

                int bottomleft_x = Math.max(bottomLeft[i][0] , bottomLeft[j][0] ) ;
                int bottomleft_y = Math.max(bottomLeft[i][1] , bottomLeft[j][1] ) ;

                int width = topRight_x - bottomleft_x ;
                int height = topRight_y - bottomleft_y ;

                int side = Math.min(width , height ) ;
                maxSide = Math.max(maxSide , side  ) ;
            }

        }

        return 1L * maxSide * maxSide  ;
    }
}