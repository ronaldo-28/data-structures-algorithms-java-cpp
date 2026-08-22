class Solution {
    int idx;  // shared index to scan the input string (exactly once) between recursion calls
    public int calculate(String s) {
        idx = 0; // everytime this method is called, index should start from 0
        return calc2(s);
    }

    public int calc2(String s) { // exact calc2 code, except two lines of logic added to handle '(' and ')'          
        int res = 0, pre = 0, num = 0, n = s.length();
        char sign = '+';
        
        while (idx < n) {
            char c = s.charAt(idx++);
            if (c == '(') num = calc2(s); // recursively calculate everything between '(' and ')'
            else if (c >= '0' && c <= '9') num = num * 10 + c - '0'; // handle numbers more than one digits
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || idx == n) { // different stop point for calculation
                switch (sign) {
                    case '+': res += pre; pre =  num; break;
                    case '-': res += pre; pre = -num; break;
                    case '*': pre *= num; break;
                    case '/': pre /= num; break;
                }
                if (c == ')') return res + pre; // finish and exit current level (recursive) processing
                num = 0;
                sign = c;
            }
        }
        
        return res + pre;
    }
}


/*
Detail of how above logic solves calculator II:
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
                                      i
                     
res:  0, pre:  0, num: 11, sign: +
res:  0, pre: 11, num:  0, sign: -
 
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
          i
res:  0, pre: 11, num:  3, sign: -
res: 11, pre: -3, num:  0, sign: +
 
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
               i
res: 11, pre: -3, num:  3, sign: +
res:  8, pre:  3, num:  0, sign: *
 
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
                     i
res:  8, pre:  3, num:  4, sign: *
res:  8, pre: 12, num:  0, sign: /
 
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
                          i
res:  8, pre: 12, num:  2, sign: /
res:  8, pre:  6, num:  0, sign: +
 
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
                               i
res:  8, pre:  6, num:  4, sign: +
res: 14, pre:  4, num:  0, sign: /
 
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
                                    i
res: 14, pre:  4, num:  2, sign: /
res: 14, pre:  2, num:  0, sign: +
 
0+11  - 3 +  3 *  4  / 2  + 4  /  2 + 5
                                      i
res: 14, pre:  2, num:  5, sign: +
res: 16, pre:  5, num:  0, sign: 5
 
 Code produced above prints:
System.out.printf("0+%s\n  %si\nres: %2d, pre: %2d, num: %2d, sign: %s\n", s, " ".repeat(i), res, pre, num, sign);
System.out.printf("res: %2d, pre: %2d, num: %2d, sign: %s\n\n", res, pre, num, sign); 
*/