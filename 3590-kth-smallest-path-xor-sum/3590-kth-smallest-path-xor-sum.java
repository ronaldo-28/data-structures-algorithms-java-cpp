class Solution {
    private static final class Query {
        private final int left, right, val, index, block;
        private Query(int left, int right, int val, int index, int len) {
            this.left = left;
            this.right = right;
            this.val = val;
            this.index = index;
            this.block = left / len;
        }
    }

    private int[] head, to, next;
    private int[] inTime, outTime, path, xor;
    private int time = 0;
    public int[] kthSmallest(int[] par, int[] vals, int[][] queries) {
        //(Mo's Algorithm) on a (Euler Tour) so we can use (Coordinate Compression) so we can perform (Square Root Decomposition) to calculate (Kth Order Statistics) scaling with the square root of the array lengths

        //Time Complexity: O(N*sqrt(Q) + Q*sqrt(N))
        
        int n = vals.length, m = queries.length, len = Math.max(1, (int)(n / Math.sqrt(m))), blockSize = (int)Math.sqrt(n);

        this.head = new int[n];
        this.to = new int[n - 1];
        this.next = new int[n - 1];
        Arrays.fill(head, -1);
        for(int i = 0; i < n - 1; i++) {
            int a = par[i + 1], b = i + 1;
            to[i] = b;
            next[i] = head[a];
            head[a] = i;
        }

        this.xor = vals;
        this.inTime = new int[n];
        this.outTime = new int[n];
        this.path = new int[n + 1];
        dfs(0, 0);

        Query[] arr = new Query[m];
        for(int i = 0; i < m; i++) arr[i] = new Query(inTime[queries[i][0]], outTime[queries[i][0]], queries[i][1], i, len);

        Arrays.sort(arr, (a, b) -> {
            if(a.block != b.block) return a.block - b.block;
            return (a.block & 1) == 0 ? a.right - b.right : b.right - a.right;
        });

        long[] sorted = new long[n];
        for(int i = 0; i < n; i++) sorted[i] = (long)xor[i] << 32 | i;
        Arrays.sort(sorted);

        int[] rank = new int[n];
        int size = 0;
        rank[(int)sorted[0]] = 0;
        for(int i = 1; i < n; i++) {
            if((sorted[i] >>> 32) != (sorted[i - 1] >>> 32)) sorted[++size] = sorted[i];
            rank[(int)sorted[i]] = size;
        }
        for(int i = 0; i <= n; i++) path[i] = rank[path[i]];

        int[] ans = new int[m];
        Arrays.fill(ans, -1);
        
        int[] freq = new int[size + 1], blockCount = new int[(n + blockSize - 1) / blockSize];
        int left = 0, right = -1, totalCount = 0;
        for(Query x : arr) {
            while(right < x.right) {
                int r = path[++right];
                if(freq[r]++ == 0) {
                    totalCount++;
                    blockCount[r / blockSize]++;
                }
            }
            while(left > x.left) {
                int r = path[--left];
                if(freq[r]++ == 0) {
                    totalCount++;
                    blockCount[r / blockSize]++;
                }
            }
            while(right > x.right) {
                int r = path[right--];
                if(--freq[r] == 0) {
                    totalCount--;
                    blockCount[r / blockSize]--;
                }
            }
            while(left < x.left) {
                int r = path[left++];
                if(--freq[r] == 0) {
                    totalCount--;
                    blockCount[r / blockSize]--;
                }
            }
            int k = x.val;
            if(totalCount < k) continue;


            int block = 0;
            while(k > blockCount[block]) k -= blockCount[block++];

            int start = block * blockSize;
            for(int i = 0; i < blockSize; i++) {
                if(freq[start + i] != 0 && --k == 0) {
                    ans[x.index] = (int)(sorted[start + i] >>> 32);
                    break;
                }
            }
        }
        return ans;
    }
    private void dfs(int index, int val) {
        inTime[index] = ++time;
        path[time] = index;
        xor[index] ^= val;
        for(int x = head[index]; x != -1; x = next[x]) dfs(to[x], xor[index]);
        outTime[index] = time;
    }
}