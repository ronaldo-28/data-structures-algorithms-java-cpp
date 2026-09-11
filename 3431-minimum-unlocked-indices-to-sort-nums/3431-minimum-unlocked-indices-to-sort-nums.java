class Solution {
    // time = O(n), space = O(1)
    public int minUnlockedIndices(int[] nums, int[] locked) {
        int n = nums.length, res = 0;
        int[][] pos = new int[3][2];
        for (int i = 0; i < 3; i++) Arrays.fill(pos[i], -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i] - 1;
            if (pos[x][0] == -1) pos[x] = new int[]{i, i};
            else pos[x][1] = i;
        }

        if (pos[0][1] != -1 && pos[2][0] != -1 && pos[0][1] > pos[2][0]) return -1;
        if (pos[1][0] != -1 && pos[0][1] != -1 && pos[1][0] < pos[0][1]) {
            for (int i = pos[1][0]; i + 1 <= pos[0][1]; i++) {
                if (locked[i] == 1) {
                    locked[i] = 0;
                    res++;
                }
            }
        }
        if (pos[1][1] != -1 && pos[2][0] != -1 && pos[2][0] < pos[1][1]) {
            for (int i = pos[2][0]; i + 1 <= pos[1][1]; i++) {
                if (locked[i] == 1) {
                    locked[i] = 0;
                    res++;
                }
            }
        }
        return res;
    }
}