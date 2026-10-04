class Solution {
    private static final int[][] dist = new int[10][10];
    static{
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                int diff = Math.abs(i-j);
                dist[i][j] = Math.min(diff,10-diff);
            }
        }
    }
    public int minRotations(int n, String s) {
        int lastdig = s.charAt(n-1)-'0';
        int ans = 0;
        int maxSaving = 0;
        int prev = 0;

        for(int k=0;k<n;k++){
            int curr = s.charAt(k)-'0';

            int original = dist[prev][curr];
            ans+=original;

            int reversed = dist[prev][lastdig];
            int currentSaving = original-reversed;
            if(currentSaving>maxSaving) maxSaving = currentSaving;

            prev = curr;
        }

        return ans-maxSaving;
    }
}