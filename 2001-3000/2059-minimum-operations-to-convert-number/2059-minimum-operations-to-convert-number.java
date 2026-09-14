class Solution {
    public int minimumOperations(int[] nums, int start, int goal) {
        int tmp = start; start = goal; goal = tmp;

        int[] dp = new int[1000+1];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer( new int[]{ 0, start });
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            int ops = arr[0];
            int cur = arr[1];
            
            ops++;
            for ( int num : nums ) {
                int sum = cur + num;
                int dif = cur - num;
                int xor = cur ^ num;

                // if ( sum == goal || dif == goal || xor == goal ) return ops;

                if ( 0 <= sum && sum <= 1000 && dp[sum] == 0) {
                    if ( sum == goal ) return ops;
                    dp[sum] = ops;
                    queue.offer(new int[]{ ops, sum });
                }
                if ( 0 <= dif && dif <= 1000 && dp[dif] == 0) {
                    if ( dif == goal ) return ops;
                    dp[dif] = ops;
                    queue.offer(new int[]{ ops, dif });
                }
                if ( 0 <= xor && xor <= 1000 && dp[xor] == 0) {
                    if ( xor == goal ) return ops;
                    dp[xor] = ops;
                    queue.offer(new int[]{ ops, xor });
                }
            }
        }
        return -1;
    }
}