
class Solution {
    public int[][] generateSchedule(int n) {
        if (n < 5) {
            return new int[0][0];
        }

        int[][] res = new int[n * (n - 1)][2];
        int k = 0;

        // Разница между номерами команд: 2, 3, ..., n - 2
        for (int d = 2; d < n - 1; d++) {
            for (int i = 0; i < n; i++) {
                res[k][0] = i;
                res[k][1] = (i + d) % n;
                k++;
            }
        }

        // Чередуем матчи с разницей +1 и -1
        for (int i = 0; i < n; i++) {
            res[k][0] = i;
            res[k][1] = (i + 1) % n;
            k++;

            res[k][0] = (i + n - 1) % n;
            res[k][1] = (i + n - 2) % n;
            k++;
        }

        return res;
    }
}