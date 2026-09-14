class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] sortedQueries = Arrays.copyOf(queries, queries.length);
        Arrays.sort(sortedQueries);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        Map<Integer, Integer> sizeMap = new HashMap<>();
        int[] result = new int[queries.length];
        int index = 0;
        for (int i = 0; i < sortedQueries.length; i++) {
            if (i != 0 && sortedQueries[i] == sortedQueries[i - 1]) {
                continue;
            }
            int query = sortedQueries[i];
            while (!pq.isEmpty() && pq.peek()[1] < query) {
                pq.poll();
            }
            while (index < intervals.length) {
                int[] interval = intervals[index];
                if (interval[0] <= query && interval[1] >= query) {
                    int size = interval[1] - interval[0] + 1;
                    pq.offer(new int[]{size, interval[1]});
                    index++;
                } else if (interval[1] < query) {
                    index++;
                } else {
                    break;
                }
            }
            if (!pq.isEmpty()) {
                sizeMap.put(query, pq.peek()[0]);
            }
        }
        for (int i = 0 ; i < queries.length; i++) {
            result[i] = sizeMap.getOrDefault(queries[i], -1);
        }
        return result;
    }
}