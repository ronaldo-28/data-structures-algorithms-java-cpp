import java.util.*;

class Solution {

    private int[] pushed, popped;
    private int n;

    private int[] stack;

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        this.pushed = pushed;
        this.popped = popped;
        this.n = pushed.length;
        this.stack = new int[n];
        return dfs(0, 0, 0); // i=push指针, j=pop指针, top=栈大小
    }

    private boolean dfs(int i, int j, int top) {
        if (j == n) return true;

        if (top > 0 && stack[top - 1] == popped[j]) {
            if (dfs(i, j + 1, top - 1)) return true;
        }

        else if (i < n) {
            stack[top] = pushed[i];
            if (dfs(i + 1, j, top + 1)) return true;
        }

        return false;
    }
}