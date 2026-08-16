class Solution {
    public int longestPath(int[] parent, String s) {
               char[] a = s.toCharArray();
        int n = a.length, res = 1;
        int[] degree = new int[n];
        for (int i = 1; i < n; i++) degree[parent[i]]++;
        int[] q = new int[n];
        int qIdx = -1;
        for (int i = 1; i < n; i++) 
            if (degree[i] == 0) q[++qIdx] = i;
        
        int[] path = new int[n]; 
        Arrays.fill(path, 1); 
        
        while (qIdx >= 0) { 
            int c = q[qIdx--];
            int p = parent[c];
            if (--degree[p] == 0 && p != 0) q[++qIdx] = p; 
            if (a[p] == a[c]) continue; 
            res = Math.max(res, path[p] + path[c]);
            path[p] = Math.max(path[p], path[c] + 1);
        }

        return res;

    }
}