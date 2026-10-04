class Solution {
    public int maxPointsInsideSquare(int[][] points, String s) {
        int[] first = new int[26];
        int[] second = new int[26];
        java.util.Arrays.fill(first, Integer.MAX_VALUE);
        java.util.Arrays.fill(second, Integer.MAX_VALUE);
        for (int i = 0; i < points.length; i++) {
            int d = Math.max(Math.abs(points[i][0]), Math.abs(points[i][1]));
            int c = s.charAt(i) - 'a';
            if (d < first[c]) {
                second[c] = first[c];
                first[c] = d;
            } else if (d < second[c]) {
                second[c] = d;
            }
        }
        int limit = Integer.MAX_VALUE;
        for (int c = 0; c < 26; c++) {
            if (second[c] != Integer.MAX_VALUE) {
                limit = Math.min(limit, second[c]);
            }
        }
        int answer = 0;
        for (int c = 0; c < 26; c++) {
            if (first[c] < limit) {
                answer++;
            }
        }
        return answer;
    }
}