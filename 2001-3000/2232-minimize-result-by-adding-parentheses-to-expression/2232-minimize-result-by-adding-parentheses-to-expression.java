class Solution {
    public String minimizeResult(String expression) {
        int n = expression.length();
        int plus = expression.indexOf('+') + 1;
        int res = Integer.MAX_VALUE;
        String ans = "";
        for (int i = 0; i < plus - 1; i++) {
            for (int j = plus; j < n; j++) {
                StringBuilder sb = new StringBuilder(expression);
                sb.insert(j + 1, ')');  
                sb.insert(i, '(');
                int a = 0, b = 0, c = 0, d = 0;
                int k = 0;
                while (k < i)   a = a * 10 + (sb.charAt(k++) - '0');
                k++; 
                while (k < plus)    b = b * 10 + (sb.charAt(k++) - '0');
                k++; 
                while (k < j + 2)   c = c * 10 + (sb.charAt(k++) - '0');
                k++; 
                while (k < n + 2)   d = d * 10 + (sb.charAt(k++) - '0');
                if (a == 0) a = 1;
                if (d == 0) d = 1;
                int total = a * (b + c) * d;
                if (total < res) {
                    res = total;
                    ans = sb.toString();
                }
            }
        }
        return ans;
    }
}