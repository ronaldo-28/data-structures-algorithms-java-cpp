class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> mapIdx = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            mapIdx.put(nums2[i], i);
        }

        int[] arr = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            arr[i] = mapIdx.get(nums1[i]);
        }
        
        return arr;
    }
}