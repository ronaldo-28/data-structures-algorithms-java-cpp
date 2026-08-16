class Solution {
    public int findMinimumTime(int[][] tasks) {
        if (tasks.length == 0) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (t1, t2) -> Integer.compare(t1[1] - t1[2] + 1, t2[1] - t2[2] + 1)
        );
        for (int[] task : tasks) {
            pq.add(new int[] { task[0], task[1], task[2] });
        }
        List<int[]> ranFor = new ArrayList<>();
        int time = 0; // ranFor.get(ranFor.size() - 1)?.[1] ?? 0
        int runTime = 0; // sumLengths(ranFor)
        while (pq.size() > 0) {
            int[] next = pq.poll();
            int initialToRun = next[2];
            if (time >= next[0]) {
                for (int i = ranFor.size() - 1; i >= 0 && next[2] > 0; i--) {
                    int[] interval = ranFor.get(i);
                    if (interval[1] < next[0]) {
                        // all the intervals are before the current task start
                        break;
                    }
                    // task could be run in parallel during this interval
                    next[2] -= interval[1] - Math.max(interval[0], next[0]) + 1;
                }
                if (next[2] <= 0) {
                    // task was completed in parallel with other tasks
                    continue;
                }
            }
            if (next[2] < initialToRun) {
                // shift task start time to avoid double-counting intervals
                next[0] = ranFor.get(ranFor.size() - 1)[1] + 1;
                pq.add(next);
                continue;
            }
            int startRun = next[1] - next[2] + 1;
            ranFor.add(new int[] { startRun, startRun + next[2] - 1 });
            time = ranFor.get(ranFor.size() - 1)[1];
            runTime += next[2];
        }
        return runTime;
    }
}