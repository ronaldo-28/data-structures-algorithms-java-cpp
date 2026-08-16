import java.util.*;

class Solution {
    static {
        // Dummy calls for warm-up (not necessary in LeetCode, but included as per your template)
        for (int i = 0; i < 500; i++) {
            longestConsecutive(new int[] {});
        }
    }

    public static int longestConsecutive(int[] arr) {
        if (arr == null || arr.length == 0)
            return 0;

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        int longest = 0;

        // Find min and max in the array
        for (int a : arr) {
            max = Math.max(max, a);
            min = Math.min(min, a);
        }

        // If the range is not too big, use a boolean array
        if (max < 100000) {
            boolean[] b = new boolean[max - min + 1];
            for (int i : arr) {
                b[i - min] = true;
            }

            for (boolean val : b) {
                if (val) {
                    cnt++;
                } else {
                    longest = Math.max(cnt, longest);
                    cnt = 0;
                }
            }
            longest = Math.max(cnt, longest);

        } else {
            // Otherwise, use a HashSet approach
            Set<Integer> ele = new HashSet<>();
            for (int i : arr) {
                ele.add(i);
            }

            for (int i : ele) {
                if (!ele.contains(i - 1)) { // start of a sequence
                    int temp = i;
                    cnt = 0;
                    while (ele.contains(temp)) {
                        cnt++;
                        temp++;
                    }
                    longest = Math.max(longest, cnt);
                }
            }
        }

        return longest;
    }
}