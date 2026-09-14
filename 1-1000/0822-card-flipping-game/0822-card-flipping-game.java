class Solution {
    public int flipgame(int[] f, int[] b) {
    boolean con[] = new boolean[2001];
    int n = f.length;
    for(int i=0; i<n; i++){
     if(f[i]==b[i]) con[f[i]] = true;
    }
    int ans = Integer.MAX_VALUE;
    for(int i=0; i<n; i++){
    if(!con[f[i]]) ans = Math.min(ans, f[i]);
    if(!con[b[i]]) ans = Math.min(ans, b[i]);
    }
    return ans==Integer.MAX_VALUE?0:ans;
    }
}