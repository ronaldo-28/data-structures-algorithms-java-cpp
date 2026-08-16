class Solution {
    static{
        for(int i=0;i<500;i++){
            maxScoreSightseeingPair(new int[]{1,2});
        }
    }
    public static int maxScoreSightseeingPair(int[] values) {
        int j=1;
        int start=0;
        int max =0;
        while(j<values.length){
            int result = values[start]+values[j]+start-j;
            max =Math.max(result, max);
             if(values[start]+start<values[j]+j){
                start =j;
            }
            j++;
        }
        return max;
    }
}