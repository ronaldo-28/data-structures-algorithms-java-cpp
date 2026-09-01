class Solution {
    private static final int[] filters = {0, 0b11111111111111, 0b1010101010101, 0b1001001001, 0b100010001, 0b10000100001, 0b1000001, 0b10000001, 1, 1, 1, 1, 1, 1, 1, 1};
    public int removeOnes(int[][] grid) {
        int n = grid.length, m = grid[0].length, state = 0, mm = (1 << m) - 1, nn = m * n;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) state |= grid[i][j] * (1 << (i * m + j));
        }
        if(state == 0) return 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(state);
        int count = 1;
        while(true) {
            for(int l = queue.size(); l > 0; l--) {
                int current = queue.poll();
                for(int i = 0; i < nn; i++) {
                    if((current & (1 << i)) > 0) {
                        int next = current & ~((filters[m] << (i % m)) | (mm << (i / m * m)));
                        if(next == 0) return count;
                        queue.offer(next);
                    }
                }
            }
            count++;
        }
    }
}