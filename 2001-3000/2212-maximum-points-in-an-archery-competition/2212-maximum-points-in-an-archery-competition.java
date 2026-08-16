class Solution {
    int max = 0, best = 0;
    public int[] maximumBobPoints(int numArrows, int[] aliceArrows) {
        backtrack(numArrows, 11, aliceArrows, 0, 0);
        int[] ans = new int[12];
        for(int i = 1; i < 12; i++) {
            if((best & (1 << i)) > 0) {
                ans[i] = aliceArrows[i] + 1;
                numArrows -= ans[i];
            }
        }
        ans[0] = numArrows;
        return ans;
    }
    private void backtrack(int numArrows, int index, int[] aliceArrows, int mask, int score) {
        if(index == 0 || numArrows == 0) {
            if(score > max) {
                max = score;
                best = mask;
            }
            return;
        }
        if(numArrows > aliceArrows[index]) backtrack(numArrows - aliceArrows[index] - 1, index - 1, aliceArrows, mask | (1 << index), score + index);
        backtrack(numArrows, index - 1, aliceArrows, mask, score);
    }
}