class Solution {
    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        int nem=0;
        for(int i=0;i<hours.length;i++){
            if(hours[i]>=target){
                nem+=1;
            }
        }
        return nem;
    }
}