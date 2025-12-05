class Solution {
 int sum=0;
    int []pre;
    int[][] rects;
    public Solution(int[][] rects) {
        this.rects=rects;
        int[] w = new int [rects.length];
         pre = new int [rects.length];
        for(int i=0;i<rects.length;i++){
            int a=rects[i][0];
            int b=rects[i][1];
            int x=rects[i][2];
            int y=rects[i][3];
            int totalpoints = (y - b + 1 ) * (x - a + 1);
            w[i] = totalpoints;
            sum+=w[i];
            pre[i] = sum ;
        }
    }
    
    public int[] pick() {
          int random=ThreadLocalRandom.current().nextInt(sum)+1;

        // int pre=0;
        // for(int i=0;i<w.length;i++){
        //     pre+=w[i];
        //     if(pre>=random){
        //         return i;
        //     }
        // }

        // binarySearch(0,pre.length-1,random);
        int i=0,j=pre.length-1;
        int ans=0;
        while(i<=j){
            int mid=(i+j)/2;
            if(pre[mid] == random){
        return findPoint(mid);
            }
             if(pre[mid] > random){
      
                 ans=mid;
                j=mid-1;
            }else{
                i=mid+1;
            }
        }

        return findPoint(ans);
    }

    int[] findPoint(int i){
                    int a=rects[i][0];
            int b=rects[i][1];
            int x=rects[i][2];
            int y=rects[i][3];
        int width  = x - a + 1;
int height = y - b + 1;
int randX = a + ThreadLocalRandom.current().nextInt(width);
int randY = b + ThreadLocalRandom.current().nextInt(height);
return new int[]{randX,randY};
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */