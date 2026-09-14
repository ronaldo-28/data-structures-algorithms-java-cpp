class Solution {
    int[][] vis;
    int n;
    char[] s;
    // same dp but using recursive way
    public int getLengthOfOptimalCompression(String str, int k) {
        s = str.toCharArray();
        n = s.length;
        vis = new int[n + 1][k + 1];
        for (int[] a : vis) Arrays.fill(a, -1);
        int res = recurse(n - 1, k);
        // for (int[] a : vis) System.out.println(Arrays.toString(a));
        return res;
    }
    
    int recurse(int i, int k) {
        // can remove all letters
        if (k >= i + 1) return 0;
        if (vis[i][k] != -1) return vis[i][k];
        // delete current character.
        int min = Integer.MAX_VALUE;
        int same = 0, diff = 0;
        for (int j = i; j >= 0; j--) {
            if (s[j] == s[i]) same++;
            else diff++;
            if (diff > k) break;
            min = Math.min(min, getLen(same) + recurse(j - 1, k - diff));
        }
        if (k > 0)
            min = Math.min(min, recurse(i - 1, k - 1));
        vis[i][k] = min;
        return min;
    }

    int getLen(int a) {
        if (a == 1) return 1;
        else if (a < 10) return 2;
        else if (a < 100) return 3;
        else return 4;
    }
}