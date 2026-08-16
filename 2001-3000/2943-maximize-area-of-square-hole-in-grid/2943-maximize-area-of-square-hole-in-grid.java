import java.util.Arrays;

public class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Arrays.sort(hBars);
        Arrays.sort(vBars);
        int mh = 1, mv = 1, c = 1;
        
        for (int i = 1; i < hBars.length; i++) {
            if (hBars[i] == hBars[i - 1] + 1)
                c++;
            else
                c = 1;
            mh = Math.max(mh, c);
        }
        
        c = 1;
        for (int i = 1; i < vBars.length; i++) {
            if (vBars[i] == vBars[i - 1] + 1)
                c++;
            else
                c = 1;
            mv = Math.max(mv, c);
        }
        
        int x = Math.min(mh + 1, mv + 1);
        return x * x;
    }
}