class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        return new java.util.AbstractList<String>(){
            List<String> res = null;
            private void build(){
                res = new ArrayList<>();
                for(String s: queries){
                    for(String t: dictionary){
                        if(dist(s, t)<=2){
                            res.add(s);
                            break;
                        }
                    }
                }
            }
            private int dist(String s, String t){
                int res = 0;
                for(int i=0;i<s.length();i++){
                    if(s.charAt(i)!=t.charAt(i)) res++;
                    if(res>2) return res;
                }
                return res;
            }
            @Override
            public String get(int idx){
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