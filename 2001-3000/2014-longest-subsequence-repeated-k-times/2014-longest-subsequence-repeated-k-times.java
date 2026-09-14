  class Solution {
    int[] freq;
    String s;
    StringBuilder result = new StringBuilder();
    StringBuilder curr = new StringBuilder();
    int k;

    public String longestSubsequenceRepeatedK(String s, int k) {

      freq = new int[26];
      for (char ch : s.toCharArray())
        freq[ch - 'a']++;

      var new_s = new StringBuilder();

      for (char ch : s.toCharArray())
        if (freq[ch - 'a'] >= k)
          new_s.append(ch);

      this.s = new_s.toString();
      this.k = k;
      backtrack();
      return result.toString();
    }

    void backtrack() {

      if (!isSubsequence(s, k, curr.toString()))
        return;

      if (curr.length() > result.length()) {
        result.setLength(0);
        result.append(curr);
      }

      for (char c = 'z'; c >= 'a'; c--) {
        if (freq[c - 'a'] >= k) {
          freq[c - 'a'] -= k;
          curr.append(c);
          backtrack();
          curr.setLength(curr.length() - 1);
          freq[c - 'a'] += k;
        }
      }
    }

    boolean isSubsequence(String s, int k, String curr) {

      if (curr.isEmpty())
        return true;

      int n = s.length(), m = curr.length();
      int j = 0;
      for (int i = 0; i < n; i++) {
        if (s.charAt(i) == curr.charAt(j)) {
          j++;
          if (j == m) {
            j = 0;
            k--;
          }
        }
      }
      return k <= 0;
    }
  }