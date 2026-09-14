class Solution {

    List<String> res = new ArrayList<>();

    void backtrack(String num, int target, int index, long value, long prevOperand, String expr) {
        if (index == num.length()) {
            if (value == target) res.add(expr);
            return;
        }

        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') break; // no leading zeros

            String part = num.substring(index, i + 1);
            long curr = Long.parseLong(part);

            if (index == 0) {
                // First number, no operator
                backtrack(num, target, i + 1, curr, curr, part);
            } else {
                // Add +
                backtrack(num, target, i + 1, value + curr, curr, expr + "+" + part);
                // Add -
                backtrack(num, target, i + 1, value - curr, -curr, expr + "-" + part);
                // Add *
                backtrack(num, target, i + 1, value - prevOperand + prevOperand * curr, 
                prevOperand * curr, expr + "*" + part);
            }
        }
    }

    public List<String> addOperators(String num, int target) {
        return new java.util.AbstractList<>(){
            List<String> res = null;
            private void build(){
                if(res!=null) return;
                res = new ArrayList<>();
                dfs(0, num, target, 0, 0,"");
            }
            private void dfs(int idx, String num, int tar, long val,long prev, String path){
                if(idx==num.length()){
                    if(val==tar){
                        res.add(path);
                    }
                    return;
                }
                for(int i=idx;i<num.length();i++){
                    if(i!=idx && num.charAt(idx)=='0') break;
                    String part = num.substring(idx, i+1);
                    long curr = Long.parseLong(part);
                    if(idx==0){
                        dfs(i+1, num, tar, curr, curr, part);
                    } else {
                        dfs(i+1, num, tar, val+curr, curr, path+"+"+part);
                        dfs(i+1, num, tar, val-curr, -curr, path+"-"+part);
                        dfs(i+1, num, tar, val-prev+prev*curr, prev*curr, path+"*"+part);
                    }
                }
            }
            @Override
            public String get(int i){
                build();
                return res.get(i);
            }
            @Override
            public int size(){
                build();
                return res.size();
            }
        };
    }
}