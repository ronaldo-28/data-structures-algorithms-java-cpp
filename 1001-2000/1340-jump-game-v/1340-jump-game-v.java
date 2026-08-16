class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;

        // 计算 arr[i] 左边最近的更大元素 arr[left[i]]
        int[] left = new int[n];
        int[] st = new int[n];
        int top = -1; // 栈顶下标
        for (int i = 0; i < n; i++) {
            int x = arr[i];
            while (top >= 0 && arr[st[top]] <= x) {
                top--; // 出栈
            }
            // 如果左边没有更大的数，或者跳跃距离超过 d，都标记为 -1
            left[i] = top < 0 || i - st[top] > d ? -1 : st[top];
            st[++top] = i; // 入栈
        }

        // 计算 arr[i] 右边最近的更大元素 arr[right[i]]
        int[] right = new int[n];
        top = -1;
        for (int i = n - 1; i >= 0; i--) {
            int x = arr[i];
            while (top >= 0 && arr[st[top]] <= x) {
                top--;
            }
            // 如果右边没有更大的数，或者跳跃距离超过 d，都标记为 -1
            right[i] = top < 0 || st[top] - i > d ? -1 : st[top];
            st[++top] = i;
        }

        int[] memo = new int[n];

        // 枚举终点，倒着跳
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, left, right, memo));
        }
        return ans;
    }

    private int dfs(int i, int[] left, int[] right, int[] memo) {
        if (i < 0) { // 没有更大的数，或者跳跃距离超过 d
            return 0;
        }
        if (memo[i] == 0) { // 没有计算过
            // 往左跳 vs 往右跳
            memo[i] = Math.max(dfs(left[i], left, right, memo), dfs(right[i], left, right, memo)) + 1;
        }
        return memo[i];
    }
}
