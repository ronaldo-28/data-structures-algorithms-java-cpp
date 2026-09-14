class Solution {
  public int findInteger(int k, int digit1, int digit2) {
    if (digit1 == 0 && digit2 == 0)
      return -1;

    int res = -1;
    int res1, res2;
    if (digit1 != 0) {
      res1 = findIntegerDFS(k, digit1, digit2, digit1);
      res = res1;
    }
    
    if (digit2 != 0) {
      res2 = findIntegerDFS(k, digit1, digit2, digit2);
      if (res2 != -1) {
        res = res == -1 ? res2 : Math.min(res, res2);
      } 
    }

    return res;
  }

  private int findIntegerDFS(int k, int digit1, int digit2, int curNum) {
    if (curNum > k && curNum % k == 0)
      return curNum;

    int res = -1;
    int res1, res2;
    if (((Integer.MAX_VALUE - digit1) / 10) >= curNum) {
      res1 = findIntegerDFS(k, digit1, digit2, curNum * 10 + digit1);
      res = res1;
    }

    if (digit1 != digit2 && ((Integer.MAX_VALUE - digit2) / 10) >= curNum) {
      res2 = findIntegerDFS(k, digit1, digit2, curNum * 10 + digit2);
      if (res2 > 0) {
        res = res == -1 ? res2 : Math.min(res, res2);
      }
    }

    return res;
  }
}