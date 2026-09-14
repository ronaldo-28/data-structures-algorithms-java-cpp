class Solution {
    private int[] roots;
    private int count;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] mat = new int[m][n];
        roots = new int[m*n];
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < m*n; i++) {
            roots[i] = i;
        }
        for(int[] pos : positions) {
            int x = pos[0], y = pos[1];
            if(1 == mat[x][y]) {
                res.add(count);
                continue;
            }
            int curr = x * n + y;
            mat[x][y] = 1;
            ++count;
            if(y > 0 && mat[x][y-1] > 0) {
                union(curr, curr - 1);
            }
            if(y < n - 1 && mat[x][y + 1] > 0) {
                union(curr, curr + 1);
            }
            if(x > 0 && mat[x - 1][y] > 0) {
                union(curr, curr - n);
            } 
            if(x < m - 1 && mat[x + 1][y] > 0) {
                union(curr, curr + n);
            } 
            res.add(count);
        }
        return res;
    }
    private int find(int tar) {
        while(tar != roots[tar]) {
            roots[tar] = roots[roots[tar]];
            tar = roots[tar];
        }
        return tar;
    }
    private void union(int a, int b) {
        int aroot = find(a), broot = find(b);
        if(aroot != broot) {
            roots[aroot] = broot;
            --count; 
        }
    }
}