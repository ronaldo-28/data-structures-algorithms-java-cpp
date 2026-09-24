class Solution {
    int[] count; 
    int distinct = 0, oddCount = 0;
        
    public boolean[] validSubarrays(int[] nums, int k, int[][] queries) {
        
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        
        int max = 0;
        for (int i = 0; i < n; ++i){
            int x = nums[i];
            prefixSum[i + 1] = prefixSum[i] ^ x;
            max = Math.max(max, x);
        }
        
        int m = queries.length;
        boolean[] ans = new boolean[m];    
            
        int[][] query = new int[m][];    
        
        int size = 0;
        for (int i = 0; i < m; ++i) {
            int[] q = queries[i];
            int l = q[0], r = q[1], len = r - l + 1;
            if( len % 2 != 0|| len < 2 * k || (prefixSum[r + 1] ^ prefixSum[l]) != 0) continue;
            query[size++] = new int[]{l, r, i};
        }  
        
        if(size == 0) return ans;
        
        query = Arrays.copyOf(query, size);
        int blockSize = (int)Math.sqrt(n) + 1;
                   
        Arrays.sort(query, (x, y)->{
            int bx = x[0] / blockSize, by = y[0] / blockSize;
            if (bx != by) return bx - by;
            if (bx % 2 == 0) return x[1] - y[1];
            return y[1] -x[1];
        });
                
        count = new int[max + 1];                 
        int l = -1, r = 0;
        
        for (int[] cur: query) {
            int ql = cur[0] - 1, qr = cur[1] + 1, index = cur[2];
            while (r < qr)
                add(nums[r++]);
             
            while (l > ql)
                add(nums[l--]);
    
            while (r > qr)
                remove(nums[--r]);
            
            while (l < ql) 
                remove(nums[++l]);
            
            ans[index] = distinct == k && oddCount == 0;
        }
        
        return ans;                              
    }
            
    void add(int x) {
        if(++count[x] == 1)
            distinct++;
        oddCount += 2 * (count[x] & 1) - 1;
    }
    
    void remove(int x) {
        if(--count[x] == 0)
            distinct--;
        oddCount += 2 * (count[x] & 1) - 1;
    }
}