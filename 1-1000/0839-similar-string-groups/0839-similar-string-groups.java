class Solution {
     char[][] ss;
    public int numSimilarGroups(String[] strs) {
        int ans = 0;
    boolean[] seen = new boolean[strs.length];

    ss = new char[strs.length][];
    for (int i = 0; i < strs.length; i++) 
        ss[i] = strs[i].toCharArray();

    for (int i = 0; i < strs.length; ++i)
      if (!seen[i]) {
        dfs(strs, i, seen);
        ++ans;
      }
    return ans;   
    }
    private void dfs(final String[] strs, int i, boolean[] seen) {
    seen[i] = true;
    for (int j = 0; j < strs.length; ++j)
      if (!seen[j] && isSimilar(i, j)){
        dfs(strs, j, seen);
      }
  }
  private boolean isSimilar( int s1,  int s2) {
      char[] st1 = ss[s1];
      char[] st2 = ss[s2];
    int diff = 0;
    for (int i = 0; i < st1.length; ++i)
      if (st1[i] != st2[i] && ++diff > 2)
        return false;
    return true;
  }
}