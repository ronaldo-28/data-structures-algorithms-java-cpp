class Solution {
    public int[] findDegrees(int[][] matrix) {
        int[] results = new int[matrix.length];

        int pointer = 0;
        for (int[] outher : matrix) {
            int count = 0;
            for (int inner : outher) {
                count += inner;
            }
            results[pointer] = count;
            pointer++;
        }

        return results;
    }
}