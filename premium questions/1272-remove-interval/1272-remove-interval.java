class Solution {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> result = new ArrayList<>();
        int removeFrom = toBeRemoved[0];
        int removeUntil = toBeRemoved[1];
        
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (end <= removeFrom || start >= removeUntil) {
                result.add(List.of(start, end));
                continue;
            }

            if (start < removeFrom) {
                result.add(List.of(start, removeFrom));
            }
            if (end > removeUntil) {
                result.add(List.of(removeUntil, end));
            }
        }

        return result;
    }
}