import java.util.Arrays;

class Solution {
    private int best;

    private void dfs(int[] masks, int chosenMask, int usedBits) {
        if (usedBits >= best) return;

        int firstUncov = -1;
        for (int i = 0; i < masks.length; ++i) {
            if ((masks[i] & chosenMask) == 0) {
                firstUncov = i;
                break;
            }
        }

        if (firstUncov == -1) {
            best = usedBits;
            return;
        }

        int cand = masks[firstUncov];
        int bestBit = 0;
        int bestGain = 0;

        for (int j = 0; j < 31; ++j) {
            int bit = 1 << j;
            if ((cand & bit) != 0) {
                int gain = 0;
                for (int i = firstUncov; i < masks.length; ++i) {
                    if ((masks[i] & chosenMask) == 0 && (masks[i] & bit) != 0) {
                        gain++;
                    }
                }
                if (gain > bestGain) {
                    bestGain = gain;
                    bestBit = bit;
                }
            }
        }

        if (bestBit != 0) {
            dfs(masks, chosenMask | bestBit, usedBits + 1);
        }

        for (int j = 0; j < 31; ++j) {
            int bit = 1 << j;
            if ((cand & bit) != 0 && bit != bestBit) {
                dfs(masks, chosenMask | bit, usedBits + 1);
            }
        }
    }

    public int minSetBits(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1;
        if (m <= 0) return -1;

        int[] lastZero = new int[31];
        Arrays.fill(lastZero, -1);
        int[] masks = new int[m];

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 31; ++j) {
                if (((nums[i] >> j) & 1) == 0) {
                    lastZero[j] = i;
                }
            }
            if (i >= k - 1) {
                int start = i - k + 1;
                int x = 0;
                for (int j = 0; j < 31; ++j) {
                    if (lastZero[j] < start) {
                        x |= (1 << j);
                    }
                }
                if (x == 0) return -1;
                masks[start] = x;
            }
        }

        best = 32;
        dfs(masks, 0, 0);
        return best >= 32 ? -1 : best;
    }
}