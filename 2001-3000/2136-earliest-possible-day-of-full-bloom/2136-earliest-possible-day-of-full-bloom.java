class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int max = 0;
        for(int i : growTime){
            max = Math.max(max,i);
        }

        int len = max;
        int[] arr = new int[len+1];
        int temp = 0;
        int minTime = 0;

        for(int i=0;i<plantTime.length;i++){
            arr[growTime[i]] += plantTime[i];
        }

        for(int i=len;i>0;i--){
            if(arr[i]!=0){
                temp += arr[i];
                minTime = Math.max(minTime,temp+i);
            }
        }
        return minTime;

    }
}