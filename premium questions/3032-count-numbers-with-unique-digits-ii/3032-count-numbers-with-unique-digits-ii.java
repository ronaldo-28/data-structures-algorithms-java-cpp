class Solution {
    private static final int LIMIT = 1000;
    /*
    * prefixCount[i] =
    * number of integers with unique digits in the range [1, i].
    */
    private static final int[] prefixCount = buildPrefixCount();

    public int numberCount(int a, int b) {
        return prefixCount[b] - prefixCount[a - 1];
    }

    private static int[] buildPrefixCount() {
        int[] prefix = new int[LIMIT + 1];

        for (int number = 1; number <= LIMIT; number++) {
            prefix[number] = prefix[number - 1];

            if (hasUniqueDigits(number)) {
                prefix[number]++;
            }
        }

        return prefix;
    }

    private static boolean hasUniqueDigits(int number) {
        boolean[] seen = new boolean[10];

        while (number > 0) {
            int digit = number % 10;

            if (seen[digit]) {
                return false;
            }

            seen[digit] = true;
            number /= 10;
        }

        return true;
    }
}