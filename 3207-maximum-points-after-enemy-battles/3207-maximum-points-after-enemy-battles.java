class Solution {
    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        long sum = 0l;
        int min = enemyEnergies[0];

        for (int e : enemyEnergies) {
            min = Math.min(min, e);
            sum += e;
        }
        if (currentEnergy < min) return 0l;
        sum -= min;
        sum += currentEnergy;
        return sum/min;

    }
}