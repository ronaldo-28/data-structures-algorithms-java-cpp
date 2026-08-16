class Solution {
    public int minCost(String colors, int[] neededTime) {
        //int n=neededTime.length;
        char[] str=colors.toCharArray();

        int i=0,j=1;
        int n=str.length;
        int time=0;

        while(j<n){
            if(str[i]==str[j]){
                if(neededTime[i]<neededTime[j]){
                    time+=neededTime[i];
                    i=j;
                    j++;
                }
                else{
                    time+=neededTime[j];
                    j++;
                }
            }
            else{
                i=j;
                j++;
            }
        }
        return time;

        
    }
}