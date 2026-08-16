class Solution {
    public List<Integer> lastVisitedIntegers(int[] nums) {
        List<Integer> seen = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int k = 0;
        int prev = 0;
        for (int el : nums) {
            if (el > 0) {
                seen.add(0, el);
                prev = 0;
                k = 0;
            } else {
                if (prev == 0) {
                    k++;
                    prev = -1;
                } else if (prev == el) {
                    k++;
                } else {
                    k = 0;
                    prev = 0;
                }
                if (k > seen.size()) {
                    ans.add(-1);
                } else {
                    ans.add(seen.get(k - 1));
                }
            }
        }
        return ans;
    }
}