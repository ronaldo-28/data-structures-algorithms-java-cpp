class Solution {
    public List<Integer> minAvailableDuration(
            int[][] slots1,
            int[][] slots2,
            int duration) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );


        for (int[] slot : slots1) {
            if (slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }

        for (int[] slot : slots2) {
            if (slot[1] - slot[0] >= duration) {
                pq.offer(slot);
            }
        }

        while (pq.size() > 1) {

            int[] first = pq.poll();
            int[] second = pq.peek();

            int start = Math.max(first[0], second[0]);
            int end = Math.min(first[1], second[1]);
            
            if (end - start >= duration) {
                return Arrays.asList(start, start + duration);
            }
        }

        return new ArrayList<>();
    }
}