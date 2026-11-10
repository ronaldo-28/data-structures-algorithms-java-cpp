class Solution {
  public long calculateScore(String[] instructions, int[] values) {
    int n = instructions.length, i = 0;
    boolean[] seen = new boolean[n];
    long score = 0;
    while (i >= 0 && i < n && !seen[i]) {
      seen[i] = true;
      if (instructions[i].charAt(0) == 'a') {
        score += values[i];
        i++;
      } else {
        i += values[i];
      }
    }
    return score;
  }
}