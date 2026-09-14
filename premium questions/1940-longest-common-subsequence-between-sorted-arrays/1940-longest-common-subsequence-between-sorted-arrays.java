import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final int[] RANGE_OF_VALUES = {1, 100};

    public List<Integer> longestCommonSubsequence(int[][] arrays) {
        int[] frequency = new int[RANGE_OF_VALUES[1] + 1];
        List<Integer> longestCommonSubsequence = new ArrayList<>();

        for (int[] numbers : arrays) {
            for (int value : numbers) {
                ++frequency[value];
            }
        }

        for (int value = RANGE_OF_VALUES[0]; value <= RANGE_OF_VALUES[1]; value++) {
            if (frequency[value] == arrays.length) {
                longestCommonSubsequence.add(value);
            }
        }

        return longestCommonSubsequence;
    }
}