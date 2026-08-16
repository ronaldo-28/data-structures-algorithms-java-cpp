class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int max_time = 0;
        for (int p : left) {
            max_time = Math.max(max_time, p);
        }
        for (int p : right) {
            max_time = Math.max(max_time, n - p);
        }
        return max_time;
    }
}