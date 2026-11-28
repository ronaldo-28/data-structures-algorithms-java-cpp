
class Solution {
    public String removeSubstring(String s, int k) {
        char[] stack = new char[s.length()];
        int[] count = new int[s.length()];

        int top = -1;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            stack[++top] = ch;

            if (ch == '(') {
                count[top] = (top > 0 && stack[top - 1] == '(')
                        ? count[top - 1] + 1 : 1;
            } else {
                count[top] = 0;
            }

            // Remove k consecutive '(' followed by k consecutive ')'
            if (ch == ')' && top >= 2 * k - 1) {
                boolean valid = true;

                for (int j = top - k + 1; j <= top; j++) {
                    if (stack[j] != ')') {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    int openStart = top - 2 * k + 1;
                    for (int j = openStart; j < openStart + k; j++) {
                        if (stack[j] != '(') {
                            valid = false;
                            break;
                        }
                    }
                }

                if (valid) {
                    top -= 2 * k;
                }
            }
        }

        return new String(stack, 0, top + 1);
    }
}