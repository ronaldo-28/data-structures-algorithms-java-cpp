class Solution {
    private static final int[] target = {0, 0, 2, 21, 228, 2930, 44790, 800667, 16434824};
    public int sortArray(int[] nums, int[] pre) {
        int n = nums.length;
        int start = getID(nums);
        if(start == target[n]) return 0;

        Map<Integer, Integer> seen = new HashMap<>();
        Deque<Integer> queue1 = new ArrayDeque<>(), queue2 = new ArrayDeque<>();
        queue1.offer(start);
        queue2.offer(target[n]);

        int val1 = 1, val2 = 2;
        seen.put(start, val1);
        seen.put(target[n], val2);

        int step = 0;
        while(!queue1.isEmpty() && !queue2.isEmpty()) {
            step++;
            if(queue2.size() < queue1.size()) {
                if(stepBFS(pre, seen, n, queue2, val2, val1)) return step;
            }else {
                if(stepBFS(pre, seen, n, queue1, val1, val2)) return step;
            }
        }
        return -1;
    }
    private static boolean stepBFS(int[] pre, Map<Integer, Integer> seen, int n, Deque<Integer> queue, int current, int other) {
        int len = queue.size();
        for(int i = 0; i < len; i++) {
            int id = queue.poll();
            for(int x : pre) {
                int rev = reverse(id, x, n);
                Integer val = seen.get(rev);
                if(val == null) {
                    seen.put(rev, current);
                    queue.offer(rev);
                }else if(val == other) return true;
            }
        }
        return false;
    }


    private static int getID(int[] arr) {
        int n = arr.length;
        int id = 0;
        for(int i = n - 1; i >= 0; i--) id = n * id + arr[i];
        return id;
    }
    private static int reverse(int id, int count, int n) {
        int pow = 1;
        int rev = 0;
        for(int i = 0; i < count; i++) {
            pow *= n;
            rev = n * rev + id % n;
            id /= n;
        }
        return id * pow + rev;
    }
}