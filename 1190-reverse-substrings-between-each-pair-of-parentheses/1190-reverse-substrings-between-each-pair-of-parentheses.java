class Solution {
    public String sol1(String s) {
        Deque<Character> dq = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != ')') {
                dq.addLast(c);
            } else {
                StringBuilder curr = new StringBuilder();
                while (!dq.isEmpty() && dq.peekLast() != '(') {
                    curr.append(dq.removeLast());
                }
                dq.removeLast();
                for (int i = 0; i < curr.length(); i++) {
                    dq.addLast(curr.charAt(i));
                }
            }
        }

        StringBuilder res = new StringBuilder();
        while (!dq.isEmpty()) {
            res.append(dq.removeFirst());
        }

        return res.toString();
    }

    public String sol2(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == ')') {
                int j = stack.pop();
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder result = new StringBuilder();
        int i = 0;
        int direction = 1;

        while (i < n) {
            char c = s.charAt(i);

            if (c == '(' || c == ')') {
                i = pair[i];
                direction = -direction;
            } else {
                result.append(c);
            }

            i += direction;
        }

        return result.toString();
    }

    public String reverseParentheses(String s) {
        return sol2(s);
    }
}