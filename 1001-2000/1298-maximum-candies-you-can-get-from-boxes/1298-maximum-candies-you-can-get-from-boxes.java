class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int candy = 0;

        for (int box : initialBoxes) {
            status[box] |= 2;
            if (status[box] == 3) {
                candy += dfs(status, candies, keys, containedBoxes, box);
            }
        }

        return candy;
    }

    int dfs(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int box) {
        int candy = candies[box];

        for (int next : keys[box]) {
            if (status[next] == 2) {
                status[next] |= 1;
                candy += dfs(status, candies, keys, containedBoxes, next);
            }
            else {
                status[next] |= 1;
            }
        }

        for (int next : containedBoxes[box]) {
            status[next] |= 2;
            if (status[next] == 3) {
                candy += dfs(status, candies, keys, containedBoxes, next);
            }
        }

        return candy;
    }
}