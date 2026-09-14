class Solution {
    public int widestPairOfIndices(int[] nums1, int[] nums2) {
        int sum = 0;
        int max = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < nums1.length; i++) {
            sum += nums1[i] - nums2[i];
            Integer index = map.get(sum);
            if (index != null) {
                max = Math.max(max, i - index);
            } else {
                map.put(sum, i);
            }
        }

        return max;
    }
}