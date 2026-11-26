import java.util.*;

class Solution {
    public int[] maxKDistinct(int[] nums, int k) {
        List<Integer> list = new ArrayList<>();

        while (k > 0) {
            int max = Integer.MIN_VALUE;

            for (int i = 0; i < nums.length; i++) {
                max = Math.max(max, nums[i]);
            }

            if (max == Integer.MIN_VALUE) {
                break;
            }

            list.add(max);

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == max) {
                    nums[i] = Integer.MIN_VALUE;
                }
            }

            k--;
        }

        int[] arr = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;
    }
}