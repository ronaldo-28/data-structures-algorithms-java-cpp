class Solution {
    public int minimumMoves(String s) {
        int n = s.length();
        int res = 0;
        for(int i=0;i<n;i++){
            if(s.charAt(i) == 'X'){
                res++;
                i = i+2;
            }
        }
        return res;
    }
}