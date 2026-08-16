class Solution {
    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeSet<Integer> set = new TreeSet<>((o1, o2) -> o1 - o2);
        int res = Integer.MAX_VALUE;
        for(int i = x; i < nums.size(); i++) {
            set.add(nums.get(i - x));
            int curr = nums.get(i);
            Integer low = set.floor(curr);
            Integer high = set.ceiling(curr);
            if (low != null) {
                res = Math.min(res, Math.abs(low - curr));
            }
            if (high != null) {
                res = Math.min(res, Math.abs(high - curr));
            }
        }
        return res;
    }
}