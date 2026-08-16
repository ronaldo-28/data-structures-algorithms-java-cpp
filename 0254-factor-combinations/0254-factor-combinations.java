class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), n, 2);
        return res;
    }

    public void dfs(List<List<Integer>> list, List<Integer> temp, int r, int start) {
        if (temp.size() > 0) {
            List<Integer> c = new ArrayList<>(temp);
            c.add(r);
            list.add(c);
        }
        for (int i = start; i * i <= r; i++) {
            if (r % i == 0) {
                temp.add(i);
                dfs(list, temp, r / i, i);
                temp.remove(temp.size() - 1);
            }
        }
    } 
}