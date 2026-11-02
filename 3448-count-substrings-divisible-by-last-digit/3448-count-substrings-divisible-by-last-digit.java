class Solution {
  public long countSubstrings(String s) {
    int n = s.length();
    long ans = 0;
    int[] cnt3 = new int[3], cnt9 = new int[9], cnt7 = new int[7];
    int sum = 0, p7 = 0, inv = 1;
    for (int i = 0; i < n; i++) {
      int d = s.charAt(i) - '0';
      // prefix state before digit i (start index l = i)
      cnt3[sum % 3]++;
      cnt9[sum % 9]++;
      cnt7[p7 * inv % 7]++;
      sum += d;
      p7 = (p7 * 10 + d) % 7;
      inv = inv * 5 % 7;
      switch (d) {
        case 1:
        case 2:
        case 5:
          ans += i + 1;
          break;
        case 3:
        case 6:
          ans += cnt3[sum % 3];
          break;
        case 9:
          ans += cnt9[sum % 9];
          break;
        case 7:
          ans += cnt7[p7 * inv % 7];
          break;
        case 4:
          ans++;
          if (i >= 1 && (10 * (s.charAt(i - 1) - '0') + d) % 4 == 0) ans += i;
          break;
        case 8:
          ans++;
          if (i >= 1 && (10 * (s.charAt(i - 1) - '0') + d) % 8 == 0) ans++;
          if (i >= 2 && (100 * (s.charAt(i - 2) - '0') + 10 * (s.charAt(i - 1) - '0') + d) % 8 == 0)
            ans += i - 1;
          break;
        default:
          break;
      }
    }
    return ans;
  }
}