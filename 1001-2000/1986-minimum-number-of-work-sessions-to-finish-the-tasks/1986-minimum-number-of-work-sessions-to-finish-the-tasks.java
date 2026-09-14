class Solution {
    int minSession;

    public int minSessions(int[] tasks, int sessionTime) {
        minSession = tasks.length; // worst case: one task per session
        int[] works = new int[minSession];
        backtrack(tasks.length - 1, tasks, works, sessionTime, 0);
        return minSession;
    }

    private void backtrack(int index, int[] tasks, int[] works, int sessionTime, int used) {
        if (index < 0) { // done with all tasks
            minSession = Math.min(minSession, used);
            return;
        }

        if (used >= minSession) return; // prune non-optimal

        int task = tasks[index]; // come from largest to smallest

        for (int i = 0; i < used; i++) {
            if (works[i] + task <= sessionTime) {
                works[i] += task;
                backtrack(index - 1, tasks, works, sessionTime, used);
                works[i] -= task;
            }
        }
        works[used] = task;
        backtrack(index - 1, tasks, works, sessionTime, used + 1);
        works[used] = 0;

    }
}