class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        //easy just line swapping technique 
        // add +2 at 1 and -2 at point 5 go on doing cumm sum if it is greater than capacity then return false
        // [0,2,2,5,5,3,3,3]
        // rather than doing this use cummulative sum 
        int trip[] = new int[1001];
        for(int t[] : trips){
            int pas = t[0];
            int start = t[1];
            int end = t[2];
            trip[start] += pas;
            trip[end] -=pas;
        }
        int cumSum = 0;
        for(int i =0;i<1001;i++){
            cumSum += trip[i];
            if(cumSum > capacity){
                return false;
            }
        }
        return true;
    }
}