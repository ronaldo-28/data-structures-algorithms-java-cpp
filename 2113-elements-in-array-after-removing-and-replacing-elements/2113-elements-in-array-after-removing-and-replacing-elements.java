class Solution {
    public int[] elementInNums(int[] nums, int[][] queries) {
        // MOD 2N
        // 
        // At minute 1 to N - 1
        // At minute N to 2N, index is same as before with x elements where minute is N+x
        
        int[] ret = new int[queries.length];
        int N = nums.length;
        final int MOD = N * 2;
        for(int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int time = query[0];
            int idx = query[1];
            
            int moddedTime = time % MOD;
            int val = 0;
            
            if(1 <= moddedTime && moddedTime < N) {
                int newIdx = idx + moddedTime;
                if(newIdx >= N) val = -1;
                else val = nums[newIdx];
            }
            else if(moddedTime == 0) {
                val = nums[idx];   
            }
            else {
                int numElements = moddedTime - N;
                if(idx >= numElements) val = -1;
                else val = nums[idx];
            }
            ret[i] = val;
        }
        return ret;
    }
}