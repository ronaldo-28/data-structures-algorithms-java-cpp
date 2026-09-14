class Solution {
    public long minEliminationTime(int[] timeReq, int splitTime) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for (int t : timeReq) {
            pq.offer((long) t);
        }

        while (pq.size() > 1) {
            pq.poll();
            pq.offer(splitTime + pq.poll());
        }

        return pq.poll();
    }
}