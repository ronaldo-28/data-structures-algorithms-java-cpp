class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        SparseTable table = new SparseTable(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>(n, (a, b) -> b[0] - a[0]);
        long sum = 0;

        for(int i = 0; i < n; i++) pq.offer(new int[] {table.query(i, n - 1), i, n - 1});

        for(int i = 0; i < k; i++) {
            int[] current = pq.poll();
            int a = current[1], b = current[2];
            sum += current[0];
            if(a < b) {
                current[2] = b - 1;
                current[0] = table.query(a, b - 1);
                pq.offer(current);
            }
        }
        return sum;
    }
}
class SparseTable {
    private final int[] pow;
    private final int[][] table1, table2;
    public SparseTable(int[] arr) {
        int n = arr.length;
        this.pow = new int[n + 1];
        for(int i = 2; i <= n; i++) pow[i] = pow[i >> 1] + 1;
        int max = pow[n];
        this.table1 = new int[max + 1][n];
        this.table2 = new int[max + 1][n];
        this.table1[0] = this.table2[0] = arr;
        for(int p = 1; p <= max; p++) {
            int len = n - (1 << p), val = 1 << (p - 1);
            for(int i = 0; i <= len; i++) {
                table1[p][i] = Math.max(table1[p - 1][i + val], table1[p - 1][i]);
                table2[p][i] = Math.min(table2[p - 1][i + val], table2[p - 1][i]);
            }
        }
    }
    public int query(int left, int right) {
        int p = pow[right - left + 1];
        return Math.max(table1[p][right - (1 << p) + 1], table1[p][left]) - Math.min(table2[p][right - (1 << p) + 1], table2[p][left]);
    }
}