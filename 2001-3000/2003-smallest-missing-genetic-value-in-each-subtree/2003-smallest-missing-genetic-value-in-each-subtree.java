class Solution {
    private int[] head, next, to;
    private int[] nums;
    private boolean[] seen;
    private int max;
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);

        int one = -1;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) {
                one = i;
                break;
            }
        }
        if(one == -1) return ans;

        this.head = new int[n];
        this.next = new int[n - 1];
        this.to = new int[n - 1];
        Arrays.fill(head, -1);
        for(int i = 0; i < n - 1; i++) {
            int a = parents[i + 1], b = i + 1;
            to[i] = b;
            next[i] = head[a];
            head[a] = i;
        }
        this.max = n + 1;
        this.nums = nums;
        this.seen = new boolean[n + 2];

        int min = 2, prev = -1;
        while(one != -1) {
            dfs(one, prev);

            while(seen[min]) min++;

            ans[one] = min;

            prev = one;
            one = parents[one];
        }
        return ans;
    }
    private void dfs(int index, int prev) {
        if(index == prev) return;
        if(nums[index] < max) seen[nums[index]] = true;

        for(int i = head[index]; i != -1; i = next[i]) dfs(to[i], prev);
    }
}