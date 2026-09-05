class Solution {
    public int minOperations(int[] nums, int x, int y) {
        // return method1(nums, x, y);
        return method2(nums, x, y);
    }

    private int method2(int[] nums, int x, int y) {
        int max = 0;
        for (int n : nums) {
            max = Math.max(max, n);
        }
        int a = 0;
        int b = (max + y - 1) / y;

        while (a < b) {
            int mid = (a + b) / 2;
            if (passForK(nums, x, y, mid)) {
                b = mid;
            } else {
                a = mid + 1;
            }
        }

        return a;
    }

    private boolean passForK(int[] nums, int x, int y, int k) {
        int ops = 0;
        for (int n : nums) {
            if (n <= y * k) {
                continue;
            }
            n -= y * k;
            ops += (n + x - y - 1) / (x - y);
            if (ops > k) {
                return false;
            }
        }

        return true;
    }

    private int method1(int[] nums, int x, int y) {

        // As x > y , upperbound of max of nums / y is at most operations
        // Apply i to the max each time, decrease by x - y
        // after n'th try, the max <= y * n -> Done
        // 
        if (nums.length == 1) {
            return (nums[0] + x - 1) / x;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int n : nums) {
            pq.add(n);
        }

        // System.out.printf("max: %d\n", pq.peek());

        int result = 0;

        while (pq.peek() > y * result) {
            int v = pq.poll();
            int tries = 1;
            // int v2 = pq.peek();
            // int tries = Math.max(1, (v - v2) / (x - y));
            // tries = Math.min(tries, v - (y * result) / (x - y));
            // System.out.printf("max: %d -> %d\n", v, v - (x - y));
            v -= (x - y) * tries;
            pq.offer(v);
            result += tries;
        }

        return result;
        
    }
}