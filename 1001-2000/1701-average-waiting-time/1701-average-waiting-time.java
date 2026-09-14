class Solution {
    public double averageWaitingTime(int[][] customers) {
        int idleTime = 0;
        long TotalWaitingtime =0; 
        for(int customer[]:customers){
            //chef is free
            if(idleTime <= customer[0]){
                idleTime = customer[0]+customer[1];
            }else{//chef is already occupied
                idleTime =idleTime+customer[1];
            }
            TotalWaitingtime += (idleTime-customer[0]);
        }    
         double avg =(TotalWaitingtime /(double)customers.length);
          return avg;  
    }
}