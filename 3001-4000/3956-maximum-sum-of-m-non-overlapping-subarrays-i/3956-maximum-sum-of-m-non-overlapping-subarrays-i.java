// class Solution {
//     public long maximumSum(int[] nums, int m, int l, int r) {
//         int n = nums.length;
//         long min = Long.MIN_VALUE >> 8;
//         // TIỀN XỬ LÝ (prefix sum)
//         long[] prefix = new long[n + 1];
        
//         for(int i = 0; i < n; i++) {
//             prefix[i + 1] = prefix[i] + nums[i];
//         }

//         // QUY HOẠCH
//         long[][] dp = new long[m + 1][n + 1];
        
//         // Base case
//         Arrays.fill(dp[1], min);

//         for(int j = 0; j < l; j++){
//             dp[1][j] = 0;
//         }

//         for(int j = l; j <= n; j++){
//             if(j > l){
//                 dp[1][j] = Math.max(dp[1][j], dp[1][j - 1]);       
//             }
//             for(int k = l; k <= r && j - k >= 0; k++){
//                 dp[1][j] = Math.max(dp[1][j], dp[0][j - k] + prefix[j] - prefix[j - k]);
//             }
//         }

//         // Main loop
//         for(int i = 2; i <= m; i++){
//             Deque<Integer> deque = new ArrayDeque<>();
//             long[] curr = dp[i], pre = dp[i - 1];
//             for(int j = l; j <= n; j++){
//                 curr[j] = pre[j];
//                 if(j > l){
//                     curr[j] = Math.max(curr[j], curr[j - 1]);       
//                 }

//                 boolean valid = !deque.isEmpty();
                
//                 int data = j - l;
//                 if(valid){
//                     int idx = deque.peekLast();
//                     while(valid && (pre[idx] - prefix[idx] <= pre[data] - prefix[data])){
//                         deque.pollLast();
//                         valid = !deque.isEmpty();
//                         idx = valid ? deque.peekLast() : 0;
//                     }
//                 }
//                 deque.addLast(data);
                
//                 int use = deque.peekFirst();
//                 curr[j] = Math.max(curr[j], prefix[j] + pre[use] - prefix[use]);
//                 if(deque.peekFirst() < j - r){
//                     deque.pollFirst();
//                 }
//             }
//         }
        
//         // Result
//         return dp[m][n];
//     }
// }

// // dp[i][j] = MAX(dp[i - 1][j - k] + sum((j - k) -> j))
// // dp[i][j] = MAX(dp[i - 1][j - k] + prefix[j] - prefix[j - k]) 
// // dp[i][j] = prefix[j] + MAX(dp[i - 1][j - k] - prefix[j - k]) 



class Solution {
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;
        long left = 0, right = 0;
        // TIỀN XỬ LÝ (prefix sum)
        long[] prefix = new long[n + 1];
        
        for(int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
            if(nums[i] > 0){
                right += nums[i];
            }
        }
        
        // Mảng bộ đệm dùng lại cho Deque tối ưu, tránh cấp phát lại liên tục trong hàm loop
        int[] q = new int[n + 2]; 
        
        long[] base = calcMaxCostWithPenalty(prefix, l, r, 0, m, q);
        if(base[1] <= m) return base[0];
        else if(base[1] - base[2] <= m){
            return base[0] - (base[1] - m);
        }
        
        while(left <= right){
            long mid = (left + right) >> 1;
            long[] calc = calcMaxCostWithPenalty(prefix, l, r, mid, m, q);
            if(calc[1] - calc[2] > m){
                left = mid + 1;
            } else if(calc[1] < m){
                right = mid - 1;
            } else {
                return calc[0] + mid * calc[1] - (calc[1] - m) * (mid + 1);
            }
        }
        return -1;
    }
    
    private long[] calcMaxCostWithPenalty(long[] prefix, int l, int r, long p, int m, int[] q){
        int n = prefix.length - 1;
        long[] dp = new long[n + 1];
        long[] choose = new long[n + 1];
        long[] base = new long[n + 1];
        
        dp[l] = prefix[l] - prefix[0] - p;
        choose[l] = 1;
        if(prefix[l] - prefix[0] - p == 1) base[l] = 1;
        
        // --- DEQUE 1 TOỐI ƯU BẰNG MẢNG PHẲNG ---
        int head = 0, tail = 0;
        q[tail++] = 0; // Tương đương dq.addLast(0)
        
        for(int i = l + 1; i <= n; i++){
            choose[i] = 1;
            // dq.peekLast() -> q[tail - 1]
            while(head < tail && prefix[q[tail - 1]] >= prefix[i - l]) {
                tail--; // Tương đương dq.pollLast()
            }
            q[tail++] = i - l; // Tương đương dq.addLast(i - l)
            
            // dq.peekFirst() -> q[head]
            if(q[head] < i - r) head++; // Tương đương dq.pollFirst()
            
            long temp = prefix[i] - p - prefix[q[head]];
            if(dp[i - 1] <= temp){
                dp[i] = temp;
                if(temp == 1) base[i] = 1;
            } else if(dp[i - 1] > temp){
                dp[i] = dp[i - 1];
                base[i] = base[i - 1];
            }
        }

        // --- DEQUE 2 TỐI ƯU BẰNG MẢNG PHẲNG ---
        head = 0; tail = 0;
        q[tail++] = 0; 

        for(int i = l + 1; i <= n; i++){
            if(dp[i] < dp[i - 1]){
                dp[i] = dp[i - 1];
                choose[i] = choose[i - 1];
                base[i] = base[i - 1];
            }
            
            int idx = q[tail - 1];
            while(
                head < tail && 
                (
                    dp[idx] - prefix[idx] < dp[i - l] - prefix[i - l] ||
                    (dp[idx] - prefix[idx] == dp[i - l] - prefix[i - l] && choose[idx] >= choose[i - l])
                ) 
            ) {
                tail--; // Tương đương dq.pollLast()
                if(head < tail) idx = q[tail - 1];
            }
            q[tail++] = i - l;
            
            if(q[head] < i - r) head++;
            idx = q[head];
            
            long temp = prefix[i] - p + dp[idx] - prefix[idx];
            
            if(temp > dp[i] || (temp == dp[i] && choose[idx] + 1 <= choose[i])){
                dp[i] = temp;
                choose[i] = choose[idx] + 1;
                base[i] = base[idx];
                if(prefix[i] - prefix[idx] - p == 1) base[i]++;
            }
        }
        return new long[] {dp[n], choose[n], base[n]};
    }
}