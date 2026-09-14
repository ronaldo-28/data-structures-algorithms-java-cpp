class Solution {
    public int[] maxUpgrades(int[] count, int[] upgrade, int[] sell, int[] money) {
        int[] answer = new int[count.length];

        for (int i = 0; i < count.length; i++) {
            // upgradeCount * upgrade = (count - upgradeCount) * sell + money
            answer[i] = Math.min(count[i], (int)(((long)count[i] * sell[i] + money[i]) / ((long)upgrade[i] + sell[i])));
        }

        return answer;
    }
}