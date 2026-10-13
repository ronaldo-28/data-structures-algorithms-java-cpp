import java.util.*;

class Solution {
    public List<Integer> maxPrimeSubset(int n, int s) {
        boolean[] comp = new boolean[n + 1];
        int[] pr = new int[n + 1];
        int m = 0;
        int total = 0;
        for(int i = 2; i <= n; i++){
            if(!comp[i]){
                pr[m++] = i;
                total += i;
                for(int j = i * i; j <= n; j += i) comp[j] = true;
            }
        }
        List<Integer> res = new ArrayList<>();

        if(total <= s){
            for(int i = 0; i < m; i++) res.add(pr[i]);
            return res;
        }

        final short INF = 10000;

        short[][] f = new short[m + 1][s + 1];
        Arrays.fill(f[m], INF);
        f[m][0] = 0;

        for (int i = m - 1; i >= 0; i--){
            int p = pr[i];
            short[] cur = f[i];
            short[] nxt = f[i + 1];
            System.arraycopy(nxt, 0, cur, 0, s + 1);
            for (int t = p; t <= s; t++){
                int v = nxt[t - p] + 1;
                if(v < cur[t]) cur[t] = (short) v;
            }
        }
        int target = 0;
        for (int t = s; t >= 1; t--){
            if(f[0][t] < INF) { target = t; break;}
        }
        if(target == 0) return res;
        
        for(int i = 0; i < m && target > 0; i++){
            int p = pr[i];
            if(target >= p && f[i + 1][target - p] + 1 == f[i][target]){
                res.add(p);
                target -= p;
            }
        }
        return res;
    }
}