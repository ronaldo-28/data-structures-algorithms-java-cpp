class Solution {
    
    
    public List<List<Integer>> combine(int n, int k) {
        return new java.util.AbstractList<List<Integer>>() {
            
            private List<List<Integer>> res;
            
            private void init() {
                res = new ArrayList();
                var builder = new Integer[k];
                combine(builder, n, 0, 0, k);
            }

            @Override
            public int size() {
                if (res == null) init();
                return res.size();
            }

            @Override
            public List<Integer> get(int index) {
                if (res == null) init();
                return res.get(index);
            }
            
            private void combine(Integer[] builder,int n, int i, int cnt, int k) {
                if (cnt == k)  res.add(List.of(builder));
                else {
                    for (int j = i + 1; j <= n; ++j) {
                        builder[cnt] = j;
                        combine(builder, n, j, cnt + 1, k);
                    }
                }
            }
        };
    }
}