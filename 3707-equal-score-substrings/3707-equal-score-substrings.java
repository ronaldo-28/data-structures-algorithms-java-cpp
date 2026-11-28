class Solution {
    public boolean scoreBalance(String s) {
        char[] letters = s.toCharArray();
        int n = letters.length - 1;
        int p = 0;

        while (p < n) {
            int leftSum  = findSumOfIntervals(letters, 0, p + 1);
            int rightSum = findSumOfIntervals(letters, p + 1, letters.length);

            if (leftSum == rightSum) return true;
            if (leftSum < rightSum) p++;
            else return false;
        }
        return false;
    }

    private int findSumOfIntervals(char[] letters, int l, int r) {
        int sum = 0;
        for (; l < r; l++) {
            sum += letters[l] - 'a' + 1;
        }
        return sum;
    }
}