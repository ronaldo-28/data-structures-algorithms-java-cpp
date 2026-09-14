class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int minLen = nums.length;

        int sum = 0;
        int head = 0;
        int tail = -1;
        final int[] deque = new int[2 * nums.length];
        for (int right = 0; right < nums.length; ++right) {
            int val = nums[right];
            sum += val;
            
            if (val >= 0) {
                deque[++tail] = right;
                deque[++tail] = val;
                if (sum >= k) {
                    while (sum - deque[head + 1] >= k) {
                        sum -= deque[head + 1];
                        head += 2;
                    }
                    minLen = Math.min(minLen, right - deque[head]);
                }
            } else if (sum <= 0) {
                sum = 0;
                head = 0;
                tail = -1;
            } else {
                for (val = -val; deque[tail] <= val; tail -= 2) {
                    val -= deque[tail];
                }
                deque[tail] -= val;
            }
        }

        return minLen < nums.length ? minLen + 1 : -1;
    }
}