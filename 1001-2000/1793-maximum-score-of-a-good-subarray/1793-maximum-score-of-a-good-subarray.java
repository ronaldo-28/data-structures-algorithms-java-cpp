class Solution {
    /*
     * #1793 Maximum Score of a Good Subarray
     */
    public static int maximumScore(int[] array, int K) {
        /*
         * so it sounds like a good subarray is one that
         * includes [K]
         *
         * the score is the minimum * the length
         *
         * so I'll keep expand until I hit a new minimum threshold
         */
        final var N = array.length;

        int i = K;
        int j = K;
        int threshold = array[K];

        int result = threshold;

        while (0 < i || j < N - 1) {
            while (0 <= i - 1 && threshold <= array[i - 1]) i--;
            while (j + 1 < N && threshold <= array[j + 1]) j++;

            result = Math.max(result, (1 + j - i) * threshold);

            if (0 < i && j + 1 < N) threshold = Math.max(array[i - 1], array[j + 1]);
            else if (0 < i) threshold = array[i - 1];
            else if (j + 1 < N) threshold = array[j + 1];
        }

        return result;
    }
}