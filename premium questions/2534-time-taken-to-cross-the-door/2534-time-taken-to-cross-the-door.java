class Solution {
    public int[] timeTaken(int[] arrival, int[] state) {
        Queue<Integer> inQue = new LinkedList<>();
        Queue<Integer> outQue = new LinkedList<>();

        int n = arrival.length;
        int[] res = new int[n];

        int prevState = -1;
        int cur = -1;
        int processed = 0;
        int i = 0;

        while (processed < n) {
            if (inQue.isEmpty() && outQue.isEmpty() && arrival[i] > cur) {
                cur = arrival[i];
                prevState = -1;
            }

            while (i < n && arrival[i] <= cur) {
                if (state[i] == 0) {
                    inQue.offer(i);
                } else {
                    outQue.offer(i);
                }
                i++;
            }

            if (prevState == 0) {
                if (!inQue.isEmpty()) {
                    int idx = inQue.poll();
                    res[idx] = cur;
                    prevState = 0;
                } else {
                    if(!outQue.isEmpty()) {
                        int idx = outQue.poll();
                        res[idx] = cur;
                        prevState = 1;
                    }
                }
            } else {
                if (!outQue.isEmpty()) {
                    int idx = outQue.poll();
                    res[idx] = cur;
                    prevState = 1;
                } else {
                    if(!inQue.isEmpty()) {
                        int idx = inQue.poll();
                        res[idx] = cur;
                        prevState = 0;
                    }
                }
            }
            cur++;
            processed++;
        }
        return res;
    }
}