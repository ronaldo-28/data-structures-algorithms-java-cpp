class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int bags = rocks.length;
        long[] space = new long[bags];
        long missing = 0;

        for (int i = 0; i < bags; i++) {
            long temp = capacity[i] - rocks[i];
            space[i] = temp;
            missing += temp;
        }

        if (missing <= additionalRocks) {
            return bags;
        }

        Arrays.sort(space);

        int count = 0;

        while (additionalRocks >= space[count]) {
            additionalRocks -= space[count];
            count++;
        }

        return count;
    }
}