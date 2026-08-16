

class Solution {
      public  int minMaxDifference(int num) {
        int div = 1;
        int temp = num;
        while (temp > 0) {
            temp /= 10;
            div *= 10;
        }
        div /= 10;
        int r = rep(num, div, 9, 0);
        int lo = rep(num, div, 0, 0);
        return r - lo;
    }
    public static int rep(int temp, int div1, int with, int j) {
        int num1 = j;
        int rep = -1;
        while (div1 > 0) {
            int dig = temp / div1;
            if(rep==-1 && dig!=with)
                rep = dig;
            if (dig == rep) {
                num1 +=   with * div1;
            } else { 
                num1 += dig * div1;
            }
            temp %= div1;
            div1 /= 10;
        }
        return num1;
    }
}