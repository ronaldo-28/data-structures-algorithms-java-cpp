class Solution {
    
    int[] heights;
    
    public int[] pourWater(int[] _heights, int water, int K) {
        this.heights = _heights;
        for(int i =0;i<water;i++){
            pourWater(K, true);
        }
        return heights;
    }
    
    private void pourWater(int index, boolean left){
        int currH = heights[index];
        if(left){
            for(int i = index-1;i>=0;i--){
                int height = heights[i];
                if(currH>height){
                    pourWater(i,true);
                    return;
                }else if(currH<height){
                    break;
                }
            }
            pourWater(index,false);
            return;
        }else{
            for(int i = index+1;i<heights.length;i++){
                int height = heights[i];
                if(currH>height){
                    pourWater(i,false);
                    return;
                }else if(currH<height){
                    break;
                }
            }
        }
        heights[index]++;
    }
}