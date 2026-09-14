/* class Solution {
    public int findMaxK(int[] nums) {
        int ans = -1; 
        for (int i : nums) {
                        for (int j : nums) {
        if (i == -j) {
          ans = Math.max(ans, Math.abs(i));}}}return ans;
}
} */class Solution {
    public int findMaxK(int[] nums) {
        boolean[] a = new boolean[1001];
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num < 0) {
                a[-num] = true;
            }
        }
        int max = -1;
        for (int num : nums) {
            if (num > max && a[num]) {
                max = num;
            }
        }
        return max;
    }
}