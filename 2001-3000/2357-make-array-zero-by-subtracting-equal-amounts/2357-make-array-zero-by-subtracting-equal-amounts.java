class Solution {
    public int minimumOperations(int[] nums) {
        boolean[] present = new boolean[101];
        for (int x: nums) present[x] = true;
        int count=0;
        for (int i=1; i<=100; i++) {
            if (present[i]) count++;
        }
        return count;
    }
}