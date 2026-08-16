class Solution {
    long max = 0;
    int count = 0, n;
    int[] left, right;
    public int countHighestScoreNodes(int[] parents) {
        n = parents.length;
        left = new int[n];
        right = new int[n];
        Arrays.fill(right, -1);
        Arrays.fill(left, -1);
        for(int i = 1; i < parents.length; i++) {
            if(right[parents[i]] == -1) right[parents[i]] = i;
            else left[parents[i]] = i;
        }
        dfs(0);
        return count;
    }
    private int dfs(int current) {
        if(current == -1) return 0;
        int l = dfs(left[current]), r = dfs(right[current]);
        int sum = l + r + 1;
        long val = l * r;
        if(l == 0 && r == 0) val = n - 1;
        else {
            if(l == 0 || r == 0) val = l + r;
            if(sum < n) val *= n - sum;
        }
        if(val > max) {
            max = val;
            count = 1;
        }else if(val == max) count++;
        return sum;
    }
}