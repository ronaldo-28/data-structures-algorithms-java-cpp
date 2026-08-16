class Solution {
    public int countSubstrings(String s, String t) {
        int m = s.length(), n = t.length();
        int out = 0;
        for(int i=0;i<m;i++)out+=helper(s, t, i, 0);
        for(int i=1;i<n;i++)out+=helper(s, t, 0, i);
        return out;
    }
    private int helper(String s, String t, int i, int j){
        int out=0, prev=0, curr=0, m=s.length(), n=t.length();
        for(    ;i<m && j<n;i++,j++){
            curr++;
            if(s.charAt(i)!=t.charAt(j)){
                prev = curr;
                curr = 0;
            }
            out+=prev;
        }
        return out;
    }
}