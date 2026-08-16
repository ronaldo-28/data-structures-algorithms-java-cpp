class Solution {
    public int countGoodRectangles(int[][] rectangles) {
        
        int count = 0, max = 0;

        for(int i = 0; i < rectangles.length; i++){
            
            int curr = Math.min(rectangles[i][0],rectangles[i][1]);
            
            if(max < curr){
                count = 1;
                max = curr;
            }else if(max == curr)
                count++;
        }
    return count;
    }
}