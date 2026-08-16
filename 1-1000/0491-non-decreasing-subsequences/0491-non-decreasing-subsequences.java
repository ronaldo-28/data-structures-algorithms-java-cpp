class Solution extends java.util.AbstractList<List<Integer>>{
    List<List<Integer>> lists;
    int[] nums;
    public List<List<Integer>> findSubsequences(int[] nums) {
        this.nums = nums;
        return this;
    }
    public void backtrack(Set<List<Integer>> res, List<Integer> curr, int i) {
        if(curr.size() >= 2) {
            res.add(new ArrayList<>(curr));
        }
        for(int j = i; j < nums.length; j++) {
            if(curr.size() == 0 || nums[j] >= curr.get(curr.size() - 1)) {
                curr.add(nums[j]);
                backtrack(res, curr, j + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }
    @Override
    public List<Integer> get(int index) {
        if(lists == null) {
            this.size();
        }
        return lists.get(index);
    }
    @Override
    public int size() {
        if(lists == null) {
            HashSet<List<Integer>> set = new HashSet<>();
            backtrack(set, new ArrayList<>(), 0);
            lists = new ArrayList<>(set);
        }
        return lists.size();
    }
}