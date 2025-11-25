class Solution {
    public String decodeString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        Deque<Integer> rpts = new ArrayDeque<>();
        Deque<StringBuilder> strs = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '[') {
                rpts.push(Integer.parseInt(sb.toString()));
                sb.setLength(0);
            } else if (c == ']') {
                int r = rpts.pop();
                String v = sb.toString();
                while (r-- > 0) {
                    strs.peek().append(v);
                }
                sb = strs.pop();
            } else {
                if (Character.isDigit(c) && (i == 0 || !Character.isDigit(s.charAt(i - 1)))) {
                    strs.push(sb);
                    sb = new StringBuilder();
                }
                sb.append(c);
            }
        }
        return sb.toString();
    }
}