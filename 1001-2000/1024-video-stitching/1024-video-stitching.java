class Solution {
    public int videoStitching(int[][] clips, int time) {
        final int n = clips.length;

        int [] maxReach = new int[time+1];

        for(int[] clip:clips){
            if(clip[0]<time){
                maxReach[clip[0]] = Math.max(maxReach[clip[0]], clip[1]);
            }
        }

        int currentEnd = 0;
        int farthest = 0;
        int count = 0;

        for(int i = 0;i<time; i++){
            farthest = Math.max(farthest, maxReach[i]);

            if(farthest<=i){
                return -1;
            }

            if(currentEnd == i){
                count++;
                currentEnd = farthest;
                if(currentEnd>=time)return count;
            }
        }
        return currentEnd>=time?count:-1;
    }
}