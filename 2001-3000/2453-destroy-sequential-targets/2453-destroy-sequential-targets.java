class Solution {
    public int destroyTargets(int[] nums, int space) {
        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0;
        for(int num : nums) max = Math.max(max, freq.merge(num % space, 1, Integer::sum));
        int ans = Integer.MAX_VALUE;
        for(int num : nums) {
            if(num < ans && freq.get(num % space) == max) ans = num;
        }
        return ans;
    }
}