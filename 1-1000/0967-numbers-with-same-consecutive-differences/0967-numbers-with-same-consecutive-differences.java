class Solution {
    int counter = 0;
    public int[] numsSameConsecDiff(int n, int k) {
        
        int[] ans = new int[9 * (2<<(n-1))];
        for(int i = 1 ; i<10 ; i++){
            recur(ans , 2 , k , n , i , i);
        }
        
        
        
        
        return Arrays.copyOfRange(ans , 0 , counter);
    }
    void recur(int[] ans , int index , int k , int n , int current , int lastdig){
        if(index == n+1){
            ans[counter++] = current;
            return;
        }
        if(lastdig+k<10){
            recur(ans , index+1 , k , n , current*10+(lastdig+k) , lastdig+k);
        }
        if(k!=0&&lastdig-k>=0){
            recur(ans , index+1 , k , n , current*10+(lastdig-k) , lastdig-k);
        }

    }
}