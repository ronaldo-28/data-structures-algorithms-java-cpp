import java.util.List;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> gridList, int health) {
        int m = gridList.size();
        int n = gridList.get(0).size();

        int[] grid = new int[m * n];
        int idx = 0;
        for (int i = 0; i < m; i++) {
            List<Integer> row = gridList.get(i);
            for (int j = 0; j < n; j++) {
                grid[idx++] = row.get(j);
            }
        }

        int[] maxHealth = new int[m * n];

        maxHealth[0] = health - grid[0];
        if (maxHealth[0] <= 0) return false;

        for (int pass = 0; pass < 4; pass++) {
            boolean changed = false;

            for (int i = 0; i < m; i++) {
                int rowOffset = i * n;
                for (int j = 0; j < n; j++) {
                    int currIdx = rowOffset + j;
                    int h = maxHealth[currIdx];
                    if (h <= 0) continue;

                    if (j + 1 < n) {
                        int nextIdx = currIdx + 1;
                        int nextH = h - grid[nextIdx];
                        if (nextH > maxHealth[nextIdx]) {
                            maxHealth[nextIdx] = nextH;
                            changed = true;
                        }
                    }
                    if (i + 1 < m) {
                        int nextIdx = currIdx + n;
                        int nextH = h - grid[nextIdx];
                        if (nextH > maxHealth[nextIdx]) {
                            maxHealth[nextIdx] = nextH;
                            changed = true;
                        }
                    }
                }
            }

            for (int i = m - 1; i >= 0; i--) {
                int rowOffset = i * n;
                for (int j = n - 1; j >= 0; j--) {
                    int currIdx = rowOffset + j;
                    int h = maxHealth[currIdx];
                    if (h <= 0) continue;

                    if (j - 1 >= 0) {
                        int nextIdx = currIdx - 1;
                        int nextH = h - grid[nextIdx];
                        if (nextH > maxHealth[nextIdx]) {
                            maxHealth[nextIdx] = nextH;
                            changed = true;
                        }
                    }
                    if (i - 1 >= 0) {
                        int nextIdx = currIdx - n;
                        int nextH = h - grid[nextIdx];
                        if (nextH > maxHealth[nextIdx]) {
                            maxHealth[nextIdx] = nextH;
                            changed = true;
                        }
                    }
                }
            }
            if (!changed) break;
        }
        return maxHealth[m * n - 1] >= 1;
    }
}