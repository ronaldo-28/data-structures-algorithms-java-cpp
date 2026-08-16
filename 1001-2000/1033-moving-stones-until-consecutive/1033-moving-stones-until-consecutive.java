import java.util.Arrays;

class Solution {
    public int[] numMovesStones(int a, int b, int c) {
        int[] stones = {a, b, c};
        Arrays.sort(stones);
        int x = stones[0];
        int y = stones[1];
        int z = stones[2];

        int gap1 = y - x - 1;
        int gap2 = z - y - 1;

        int min_moves = 0;
        if (gap1 == 0 && gap2 == 0) {
            min_moves = 0;
        } else if (gap1 <= 1 || gap2 <= 1) {
            min_moves = 1;
        } else {
            min_moves = 2;
        }

        int max_moves = gap1 + gap2;

        return new int[]{min_moves, max_moves};
    }
}