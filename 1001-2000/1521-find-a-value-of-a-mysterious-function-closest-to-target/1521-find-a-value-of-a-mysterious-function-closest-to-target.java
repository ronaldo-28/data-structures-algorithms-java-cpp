class Solution {
    public int closestToTarget(int[] A, int k) {
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            res = Math.min(res, Math.abs(A[i] - k));
            for (int j = i - 1; j >= 0 && (A[j] & A[i]) != A[j]; j--) {
                A[j] &= A[i];
                res = Math.min(res, Math.abs(A[j] - k));
            }
        }
        return res;
    }
}