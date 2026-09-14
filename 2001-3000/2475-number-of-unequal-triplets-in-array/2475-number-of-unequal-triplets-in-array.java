class Solution {
        public int unequalTriplets(int[] A) {
        int trips = 0, pairs = 0, count[] = new int[1001];
        for (int i = 0; i < A.length; ++i) {
            trips += pairs - count[A[i]] * (i - count[A[i]]);
            pairs += i - count[A[i]];
            count[A[i]] += 1;
        }
        return trips;
    }
}