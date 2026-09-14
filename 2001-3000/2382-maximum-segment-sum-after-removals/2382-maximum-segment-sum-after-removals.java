class Solution {

    int[] roots;
    long[] segSum;

    public long[] maximumSegmentSum(int[] nums, int[] removeQueries) {
        int len = nums.length;
        roots = new int[len];
        segSum = new long[len];
        boolean[] restored = new boolean[len];
        long[] ans = new long[len];

        for (int i = 0; i < len; i++) {
            roots[i] = i;
            segSum[i] = nums[i];
        }

        long segMax = 0;
        for (int i = len - 1; i >= 0; i--) {
            ans[i] = segMax;

            int removeId = removeQueries[i];
            restored[removeId] = true;

            int leftId = removeId - 1, rightId = removeId + 1;
            if (leftId >= 0 && restored[leftId]) {
                union(removeId, leftId);
            }
            if (rightId < len && restored[rightId]) {
                union(removeId, rightId);
            }

            segMax = Math.max(segMax, segSum[removeId]);
        }
        return ans;

    }

    public int find(int i) {
        if (roots[i] == i)
            return i;
        return roots[i] = find(roots[i]);

    }

    public void union(int a, int b) {
        int rA = find(a), rB = find(b);
        if (rA != rB) {
            roots[rB] = rA;
            segSum[rA] += segSum[rB];
        }
    }
}