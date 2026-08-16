class Solution {
  public boolean canPermutePalindrome(String s) {
    int[] fr = new int[26];
    for (char c : s.toCharArray()) fr[c - 'a']++;
    int oddCount = 0;
    for (int frequency : fr)
      if (frequency % 2 == 1)
        oddCount++;
    return oddCount <= 1;
  }
}