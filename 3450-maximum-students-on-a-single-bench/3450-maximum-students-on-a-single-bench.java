class Solution {
    public int maxStudentsOnBench(int[][] students) {
        Set<Integer>[] set = new HashSet[101];
        for (int i = 0; i < set.length; i++) set[i] = new HashSet<>();
        int maxSize = 0;
        for (int[] s : students) {
            set[s[1]].add(s[0]);
            maxSize = Math.max(maxSize, set[s[1]].size());
        }

        return maxSize;
    }
}