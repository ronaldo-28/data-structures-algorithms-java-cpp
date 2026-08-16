class Solution {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int n=customers.length;
        int wait=0;
        int pro=0;
        int max=0;
        int cnt=1;
        int ans=-1;
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            wait+=customers[i];
            if(wait<=4){
                pro+=boardingCost*wait;
                wait=0;
            }
            else{
                pro+=boardingCost*4;
                wait-=4;
            }
            // pro+=boardingCost*wait;
            pro-=runningCost;
            if(pro>max){
                max=pro;
                ans=cnt;
            }
            cnt++;
        }
        while(wait>0){
            if(wait<=4){
                pro+=boardingCost*wait;
                wait=0;
            }
            else{
                pro+=boardingCost*4;
                wait-=4;
            }
            pro-=runningCost;
            if(pro>max){
                max=pro;
                ans=cnt;
            }
            cnt++;
            
        }
        return ans;
    }
}