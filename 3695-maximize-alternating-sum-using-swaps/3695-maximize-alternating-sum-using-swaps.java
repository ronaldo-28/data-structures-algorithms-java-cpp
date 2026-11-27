class Solution {
    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        int n = nums.length;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] s : swaps) {
            int a = find(parent, s[0]);
            int b = find(parent, s[1]);
            if (a != b) parent[a] = b;
        }
        int[] root = new int[n];
        int[] size = new int[n];
        int[] evens = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = find(parent, i);
            size[root[i]]++;
            if (i % 2 == 0) evens[root[i]]++;
        }
        int[] start = new int[n + 1];
        for (int i = 0; i < n; i++) start[i + 1] = start[i] + size[i];
        int[] pos = Arrays.copyOf(start, n);
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) vals[pos[root[i]]++] = nums[i];
        long ans = 0;
        for (int r = 0; r < n; r++) {
            if (size[r] == 0) continue;
            Arrays.sort(vals, start[r], start[r + 1]);
            int cut = start[r + 1] - evens[r];
            for (int i = start[r]; i < start[r + 1]; i++) {
                ans += i >= cut ? vals[i] : -vals[i];
            }
        }
        return ans;
    }

    int find(int[] parent, int x) {
        while (parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}