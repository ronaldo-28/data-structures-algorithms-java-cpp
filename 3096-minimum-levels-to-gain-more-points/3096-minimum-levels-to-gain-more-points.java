class Solution {
    public int minimumLevels(int[] possible) {
        int remainingPoints = 0;
        for (int level: possible) {
            remainingPoints += level;
        }
        remainingPoints -= possible.length - remainingPoints;
        int answer = 0;
        int points = 0;
        int n = possible.length;
        for (int i = 0; i < n - 1; i++) {
            int d = (possible[i] << 1) - 1;
            points += d;
            remainingPoints -= d;
            if (points > remainingPoints) {
                return i + 1;
            }
        }
        return -1;
    }
}