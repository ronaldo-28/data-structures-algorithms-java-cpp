class Solution {
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int[] pos = new int[nums.length];
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                pos[count++] = i;
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int q = queries[i];
            ans[i] = (q <= count) ? pos[q - 1] : -1;
        }
        return ans;
    }
}