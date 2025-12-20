class Solution {
    
    public int minSteps(int n) {
        if(n==1)return 0;
         int sum=0;
        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                 sum+=i;
                n = n / i;
            }
        }
       
        
        return sum;
    }
   
}