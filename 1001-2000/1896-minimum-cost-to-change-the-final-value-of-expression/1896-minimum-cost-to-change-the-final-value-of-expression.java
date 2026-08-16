class Solution {
    public int minOperationsToFlip(String expression) {
        char[] exp = expression.toCharArray();
        int n = exp.length;

        int[] match = new int[n];
        java.util.Deque<Integer> stack = new java.util.ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (exp[i] == '(') {
                stack.push(i);
            } else if (exp[i] == ')') {
                int j = stack.pop();
                match[j] = i;
            }
        }

        Node root = parse(exp, 0, n - 1, match);
        return root.val == 0 ? root.costTo1 : root.costTo0;
    }

    static class Node {
        int val;
        int costTo0, costTo1;

        Node(int v) {
            val = v;
            costTo0 = (v == 0 ? 0 : 1);
            costTo1 = (v == 1 ? 0 : 1);
        }

        Node(int v, int c0, int c1) {
            val = v;
            costTo0 = c0;
            costTo1 = c1;
        }
    }

    private Node parse(char[] exp, int l, int r, int[] match) {
        // remove full outer parentheses repeatedly
        while (l < r && exp[l] == '(' && match[l] == r) {
            l++;
            r--;
        }

        // parse first operand
        int i = l;
        Node curr;

        if (exp[i] == '(') {
            int j = match[i];
            curr = parse(exp, i + 1, j - 1, match);
            i = j + 1;
        } else {
            curr = new Node(exp[i] - '0');
            i++;
        }

        // keep combining left-to-right
        while (i <= r) {
            char op = exp[i++];
            Node next;

            if (exp[i] == '(') {
                int j = match[i];
                next = parse(exp, i + 1, j - 1, match);
                i = j + 1;
            } else {
                next = new Node(exp[i] - '0');
                i++;
            }

            curr = combine(curr, next, op);
        }

        return curr;
    }

    private Node combine(Node A, Node B, char op) {
        if (op == '&') {
            int val = A.val & B.val;
            int costTo0 = Math.min(Math.min(A.costTo0, B.costTo0), 1 + A.costTo0 + B.costTo0);
            int costTo1 = Math.min(A.costTo1 + B.costTo1, 1 + Math.min(A.costTo1, B.costTo1));
            return new Node(val, costTo0, costTo1);
        } else { // '|'
            int val = A.val | B.val;
            int costTo0 = Math.min(A.costTo0 + B.costTo0, 1 + Math.min(A.costTo0, B.costTo0));
            int costTo1 = Math.min(Math.min(A.costTo1, B.costTo1), 1 + A.costTo1 + B.costTo1);
            return new Node(val, costTo0, costTo1);
        }
    }
}