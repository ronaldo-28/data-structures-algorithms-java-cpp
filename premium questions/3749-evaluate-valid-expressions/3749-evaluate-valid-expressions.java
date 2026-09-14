class Solution {
    // time = O(n), space = O(n)
    String s;
    int idx;
    public long evaluateExpression(String expression) {
        s = expression;
        idx = 0;
        return dfs();
    }

    private long dfs() {
        if (s.charAt(idx) == '-' || Character.isDigit(s.charAt(idx))) return eval();
        char c = s.charAt(idx);
        idx += 3; // skip "add", "sub", "mul" or "div"
        idx++; // skip '('
        long l = dfs();
        idx++; // skip ','
        long r = dfs();
        idx++; // skip ')'
        long res = 0;
        if (c == 'a') res = l + r;
        else if (c == 's') res = l - r;
        else if (c == 'm') res = l * r;
        else res = l / r;
        return res;
    }

    private long eval() {
        int sign = 1;
        if (s.charAt(idx) == '-') {
            sign = -1;
            idx++;
        }
        long v = 0;
        int n = s.length();
        while (idx < n && Character.isDigit(s.charAt(idx))) {
            v = v * 10 + (s.charAt(idx) - '0');
            idx++;
        }
        return v * sign;
    }
}