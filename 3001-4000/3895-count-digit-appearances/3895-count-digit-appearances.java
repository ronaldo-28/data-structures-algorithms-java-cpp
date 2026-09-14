class Solution {
    public int countDigitOccurrences(int[] nums, int digit) {
        int d[]=new int[10];
        for(int num:nums){ 
            while(num>0){
                int n=num%10;
                d[n]+=1;
                num/=10;
            }
        }
        return d[digit];
    }
}