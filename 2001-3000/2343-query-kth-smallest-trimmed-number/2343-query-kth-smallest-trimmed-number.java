class Solution {
    private int[] sort(List<int[]> sorted, String[] nums, int k) {
        if (k > nums[0].length()) k = nums[0].length();
        while (sorted.size() <= k) {
            int[] t = new int [nums.length];
            int l = sorted.size();
            int charPos = nums[0].length() - l;
            int[] count = new int[10];
            for (String s: nums) {
                count[s.charAt(charPos) - '0']++;
            }
            int[] pos =new int[10];
            for (int i = 0; i < 9; i++) {
                pos[i +  1] = count[i] + pos[i];
            }
            for (int i: sorted.get(l - 1)) {
                t[pos[nums[i].charAt(charPos) - '0']++] = i;
            }
            sorted.add(t);
        }
        return sorted.get(k);
    }
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        List<int []> sorted = new ArrayList<>();
        int[] res = new int[queries.length];
        int[] t = new int [nums.length];
        for (int i = 0; i < t.length; i++) {
            t[i] = i;
        }
        sorted.add(t);
        for (int i = 0; i <  queries.length; i++) {
            res[i] = sort(sorted, nums, queries[i][1])[queries[i][0] - 1];
        }
        return res;
    }
}