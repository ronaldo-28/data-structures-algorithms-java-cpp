class Solution {
    public int[] getNoZeroIntegers(int n) {
      
  
       for(int i=1;i<=n;i++){
            int a = i;
            int b = n-i;
            if(a>0 && b>0 && iscontainsZero(a) == false && iscontainsZero(b) == false){
                return new int[]{a,b};
            }
       }
    
    return new int[] {-1,-1};
    }
    public boolean iscontainsZero(int n){
        while(n > 0){
            int digits = n % 10;
            if(digits == 0) return true;
            n /= 10;
        }
        return false;
    }
}