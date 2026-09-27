class Solution {

    public int countGroups(int[] position, int[] speed, int distance) {

        int n = position.length;

        int group = 1;
        int curSpeed = speed[n - 1];

        for (int i = n - 2; i >= 0; i--) {

            if (position[i + 1] - position[i] <= distance
                    || speed[i] > curSpeed) {
                continue;
            }

            group++;
            curSpeed = speed[i];
        }

        return group;
    }
}