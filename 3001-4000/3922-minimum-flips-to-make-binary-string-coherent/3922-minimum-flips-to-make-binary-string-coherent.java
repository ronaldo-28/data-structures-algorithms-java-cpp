class Solution {
    public int minFlips(String s) {
        int n = s.length();
        if(n<3) return 0;

        int count=0;
        for(int i=0;i<n;i++){
            count+= s.charAt(i) & 1;
        }

        return Math.min(n-count, Math.max(0, count-1- (s.charAt(0) & s.charAt(n-1)&1)));
    }
}