import java.util.*;

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int tuplesCount = 0;
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            Arrays.sort(nums3);
            Arrays.sort(nums4);
            int minSum34 = nums3[0] + nums4[0];
            int maxSum34 = nums3[nums3.length-1] + nums4[nums4.length-1];
            HashMap<Integer, Integer> map = new HashMap<>(nums1.length * nums2.length * 4/3);
            for (int a : nums1) {
                for (int b : nums2) {
                    int sum12 = a + b;
                    if (-sum12 >= minSum34 && -sum12 <= maxSum34) {
                        map.merge(sum12, 1, Integer::sum);
                    }
                }
            }
            for (int c : nums3) {
                for (int d : nums4) {
                    tuplesCount += map.getOrDefault(-(c + d), 0);
                }
            }
            return tuplesCount;
    }
}