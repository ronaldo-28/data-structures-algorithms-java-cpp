class Solution {
    public int ans = 0;
    public int CalculateIndex(int[] days, int index, int val){
        int find = days[index] + val;
        for(int i = index; i<days.length; i++){
            if(days[i] >= find){
                return i;
            }
        }
        return days.length;
    }
    public int findValue(int[] days, int[] costs, int i,  int dp[]){
        if(i >= days.length){
            return 0;
        }
        if(dp[i] != 0){
            return dp[i];
        }
    
        int day1 = costs[0] + findValue(days, costs, i+1, dp);
        int ind7 = CalculateIndex(days, i, 7);
        int day7 = costs[1] + findValue(days, costs, ind7, dp);
        int ind30 = CalculateIndex(days, i, 30);        
        int day20 = costs[2] + findValue(days, costs, ind30, dp);
        
        return dp[i] =  Math.min(Math.min(day1, day7), day20);
    }
    public int mincostTickets(int[] days, int[] costs) {
        int max = 0;
        for(int i = 0; i<days.length; i++){
            if(max < days[i]){
                max = days[i];
            }
        }
        int[] dp = new int[days.length+1];
        return findValue(days, costs, 0, dp);   
    }
}