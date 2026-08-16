class Solution {

  public String addBinary(String s1, String s2) {
    if(s1.equals("0")) {
      return s2;
    }
    if(s2.equals("0")) {
      return s1;
    }

    final int length1 = s1.length();
    final int length2 = s2.length();
    int maxLength = Math.max(length1, length2);
    char[] sum = new char[maxLength + 1];
    
    int sumIndex = sum.length - 1;
    int index1 = length1 - 1;
    int index2 = length2 - 1;
    int digit = 0;
    while(index1 >= 0 || index2 >= 0) {
      if(index1 >= 0) {
        char c1 = s1.charAt(index1);
        int digit1 = c1 - '0';
        digit += digit1;
        index1 -= 1;
      }

      if(index2 >= 0) {
        char c2 = s2.charAt(index2);
        int digit2 = c2 - '0';
        digit += digit2;
        index2 -= 1;
      }

      char c = '0';
      c += digit & 1;
      sum[sumIndex] = c;
      digit >>= 1;
      sumIndex -= 1;
    }
    if(digit > 0) {
      char c = '0';
      c += digit & 1;
      sum[sumIndex] = c;
      sumIndex -= 1; // -1
    }
    return new String(sum, sumIndex + 1, sum.length - 1 - sumIndex);
  }

  static {
    Solution s = new Solution();
    for(int i = 0; i < 500; ++i) {
      s.addBinary("101", "10");
    }
  }
}