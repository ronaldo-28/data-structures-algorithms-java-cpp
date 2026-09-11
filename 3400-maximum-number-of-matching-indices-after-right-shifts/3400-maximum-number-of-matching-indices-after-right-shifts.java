class Solution {
    public int maximumMatchingIndices(int[] nums1, int[] nums2) {
        int length = nums1.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            map.computeIfAbsent(nums1[i], k -> new ArrayList<>()).add(i);
        }

        int[] swaps = new int[length];
        int maxMatches = 0;
        for (int i = 0; i < length; i++) {
            List<Integer> indicies = map.getOrDefault(nums2[i], new ArrayList<>());
            for (int index : indicies) {
                int swapIndex = (index - i + length) % length;
                swaps[swapIndex]++;
                maxMatches = Math.max(maxMatches, swaps[swapIndex]);
            }
        }
        return maxMatches;
    }
}