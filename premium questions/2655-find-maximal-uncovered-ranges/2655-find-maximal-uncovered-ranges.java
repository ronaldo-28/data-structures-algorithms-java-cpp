class Solution {
    public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {

        Arrays.sort(ranges, (a, b) -> a[0] - b[0]);

        List<int[]> merged = new ArrayList<>();

        for (int[] range : ranges) {

            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < range[0] - 1) {
                merged.add(new int[]{range[0], range[1]});
            } else {
                merged.get(merged.size() - 1)[1] =
                        Math.max(merged.get(merged.size() - 1)[1], range[1]);
            }
        }

        List<int[]> ans = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : merged) {

            if (prevEnd + 1 <= interval[0] - 1) {
                ans.add(new int[]{prevEnd + 1, interval[0] - 1});
            }

            prevEnd = interval[1];
        }

        if (prevEnd + 1 <= n - 1) {
            ans.add(new int[]{prevEnd + 1, n - 1});
        }

        return ans.toArray(new int[ans.size()][]);
    }
}