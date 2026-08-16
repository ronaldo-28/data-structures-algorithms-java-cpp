class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] seen = new int[101];
        List<Integer> ans = new ArrayList<Integer>();
        for (int x: nums1) seen[x] = 1;
        for (int x: nums2) {
            if (seen[x] == 1) {
                ans.add(x);
                seen[x] = 3;
            } else 
                seen[x] = seen[x]|2;
        }
        for (int x: nums3) {
            if (seen[x]==1 || seen[x]==2) {
                ans.add(x);
                seen[x] = 5;
            } else
                seen[x] = seen[x]|4;
        }
        return ans;
    }
}