class Solution {
     static {
        for (int i = 0; i < 600; i++) {
            findRelativeRanks(new int[] {1, 2, 3, 4});
        }
    }
    public static String[] findRelativeRanks(int[] score) {
        int max = -1;
        for (int maxScore : score) {

            max = Math.max(max, maxScore);
        }

        int[] map = new int[max + 1];

        for (int i = 0; i < score.length; i++) {

            int index = score[i];
            map[index] = i + 1;

        }

        String res[] = new String[score.length];
        int rank = 1;
        for (int i = map.length - 1; i >= 0; i--) {
            if (map[i] != 0) {
                int originalIndex = map[i] - 1;
                if (rank == 1) {
                    res[originalIndex] = "Gold Medal";
                } else if (rank == 2) {
                    res[originalIndex] = "Silver Medal";
                } else if (rank == 3) {
                    res[originalIndex] = "Bronze Medal";
                } else {
                    res[originalIndex] = Integer.toString(rank);
                }
                rank++;
            }
            if (rank > res.length) {
                break;
            }
        }
        return res;
    }
}