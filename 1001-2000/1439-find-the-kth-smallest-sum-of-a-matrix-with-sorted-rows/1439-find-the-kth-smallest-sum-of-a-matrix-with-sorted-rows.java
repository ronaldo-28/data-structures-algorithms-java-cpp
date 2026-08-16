class Solution {
    private int leftK;

    public int kthSmallest(int[][] mat, int k) {
        int low = 0, high = 0;
        // Calculate minimum and maximum possible sums.
        for (int[] row : mat) {
            low += row[0];
            high += row[row.length - 1];
        }
        int min = low;  // Minimum possible sum.
        low -= 1;       // Set low to one less than the minimum.
        
        // Binary search over the sum range.
        while (low < high) {
            int mid = low + (high - low) / 2;
            leftK = k;  // Reset global counter for kth sum.
            // DFS to count how many selections yield a sum difference <= (mid - min)
            if (dfs(mat, mat.length - 1, mid - min)) {
                high = mid;  // Candidate threshold is high enough; try a smaller sum.
            } else {
                low = mid + 1;  // Increase the threshold.
            }
        }
        return low;  // Return the kth smallest sum.
    }

    private boolean dfs(int[][] mat, int i, int s) {
        // If all rows have been processed, decrement leftK and check if we've reached the kth sum.
        if (i < 0) {
            return --leftK == 0;
        }
        // For the current row, try every candidate value such that the extra cost (val - smallest in row) does not exceed s.
        for (int val : mat[i]) {
            if (val - mat[i][0] > s) break;  // Since the row is sorted, no further values will fit.
            if (dfs(mat, i - 1, s - (val - mat[i][0]))) {
                return true;
            }
        }
        return false;
    }
}

/*class Solution {
    public int kthSmallest(int[][] mat, int k) {
        List<Integer> values = new ArrayList<>();
        int rows = mat.length, cols = Math.min(mat[0].length, k);
        for (int i = 0; i < cols; i++)
            values.add(mat[0][i]);
        for (int i = 1; i < rows; i++)
            values = findKSmallest(values, mat[i], cols, k);
        return values.get(k-1);
    }
    
    private List<Integer> findKSmallest(List<Integer> values, int[] row, int cols, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0]+a[1]-b[0]-b[1]));
        for (int v: values)
            pq.offer(new int[]{v, row[0], 0});
        List<Integer> ans = new ArrayList<>();
        while (ans.size() < k && !pq.isEmpty()) {
            int[] pair = pq.poll();
            ans.add(pair[0] + pair[1]);
            if (pair[2] + 1 < cols)
                pq.offer(new int[]{pair[0], row[pair[2]+1], pair[2]+1});
        }
        return ans;
    }
}*/

/*class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int C = Math.min(mat[0].length, k);

        var maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        maxHeap.add(0);
        for (var row : mat) {
            // max heap for the i-th row
            var nextHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
            for (int prevSum : maxHeap) {
                for (int c = 0; c < C; c++) {
                    nextHeap.add(prevSum + row[c]);
                    // keep next max heap size <= k
                    if (nextHeap.size() > k) {
                        nextHeap.poll();
                    }
                }
            }
            maxHeap = nextHeap;
        }
        return maxHeap.peek();
    }
}*/

/*class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length, sum = 0;
        List<Integer> lockedInRowVals = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> x[0] - y[0]);

        for(int r = 0; r < m; r++){
            pq.offer(new int[] {mat[r][1], r , 0});
        }

        for(int i = 1; i < Math.min(k, m * n - m); i++){
            int[] rowInfoToAdvance = pq.poll();
            int row = rowInfoToAdvance[1];
            int nextCol = rowInfoToAdvance[2] + 1;
            if(nextCol == n - 1){
                lockedInRowVals.add(mat[row][nextCol]);
            } else{
                pq.offer(new int[] { mat[row][nextCol], row, nextCol });
            }
        }

        for(int v : lockedInRowVals){
            sum += v;
        }

        while(!pq.isEmpty()){
            int[] rowInfo = pq.poll();
            sum += rowInfo[0];
        }

        return sum;
    }
}*/

/*class Solution {
    public int kthSmallest(int[][] mat, int k) {
        int[] prev = mat[0];

        for (int i = 1; i < mat.length; i++) {
            prev = merge(prev, mat[i], k);
        }

        return prev[k - 1];
    }

    private int[] merge(int[] a, int[] b, int k) {
        int n = a.length, m = b.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (x, y) -> x[0] - y[0]
        );

        for (int i = 0; i < Math.min(n, k); i++) {
            pq.offer(new int[]{a[i] + b[0], i, 0});
        }

        int size = Math.min(k, n * m);
        int[] result = new int[size];

        for (int idx = 0; idx < size; idx++) {
            int[] curr = pq.poll();
            result[idx] = curr[0];

            int i = curr[1];
            int j = curr[2];

            if (j + 1 < m) {
                pq.offer(new int[]{a[i] + b[j + 1], i, j + 1});
            }
        }

        return result;
    }
}*/