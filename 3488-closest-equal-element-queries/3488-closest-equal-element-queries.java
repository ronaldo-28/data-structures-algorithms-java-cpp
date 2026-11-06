class Solution {
    public List<Integer> solveQueries(int[] nums, int[] q) {
        return new java.util.AbstractList<Integer>() {
            List<Integer> res = null;
            private int lowerB(List<Integer> list, int x){
                int l = 0, h=list.size();
                while(l<h){
                    int mid = l+(h-l)/2;
                    if(list.get(mid)<x) l=mid+1;
                    else h=mid;
                }
                return l;
            }
            private int dist(int n, int i, int j){
                int d = Math.abs(i-j);
                return Math.min(d, n-d);
            }
            private void build(){
                int qn = q.length;
                res = new ArrayList<>(qn);
                int n= nums.length;
                var map = new HashMap<Integer, List<Integer>>();
                for(int i=0;i<n;i++){
                    map.computeIfAbsent(nums[i], k->new ArrayList<>()).add(i);
                }
                for(int x: q){
                    List<Integer> l = map.get(nums[x]);
                    int ls = l.size();
                    if(ls==1){
                        res.add(-1);
                        continue;
                    }
                    int lb = lowerB(l, x);
                    int best = Integer.MAX_VALUE;
                    int p = l.get((lb-1+ls)%ls);
                    int nxt = l.get((lb+1)%ls);
                    best = Math.min(best, dist(n, x, p));
                    best = Math.min(best, dist(n, x, nxt));
                    res.add(best);
                }
            }
            @Override
            public Integer get(int idx){
                if(res==null) build();
                return res.get(idx);
            }
            @Override
            public int size(){
                if(res==null) build();
                return res.size();
            }
        };
    } 
}