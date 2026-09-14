class Solution {
    public int maxLength(int[] ribbons, int k) {
        long totalLength = 0;

        for (int ribbon : ribbons) {
            totalLength += ribbon;
        }

        long minLength = 1;
        long maxLength = totalLength / k;
        long result = 0;

        while (minLength <= maxLength) {
            long midLength = (minLength + maxLength) / 2;
            int total = 0;

            for (int ribbon : ribbons) {
                total += ribbon / midLength;
            }

            if (total >= k) {
                result = Math.max(midLength, result);
                minLength = midLength + 1;
            } else {
                maxLength = midLength - 1;
            }
        }

        return (int) result;
    }
}