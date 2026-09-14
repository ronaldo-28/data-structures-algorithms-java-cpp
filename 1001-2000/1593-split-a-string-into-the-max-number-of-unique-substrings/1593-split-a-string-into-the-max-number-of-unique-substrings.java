class Solution {
    public int maxUniqueSplit(String s) {
        int [] ans = {0};
        helper(0, s, new HashSet<>(), ans);

        return ans[0];
    }

    public void helper(int i, String s, Set<String> set, int [] ans){
        if(i == s.length()) {
            ans[0] = Math.max(ans[0], set.size());
            return;
        }

        for(int j = i ; j < s.length() ; j++){
            if((set.size() + s.length() - (j + 1)) < ans[0]) break;

            String temp = s.substring(i, j + 1);

            if(!set.contains(temp)){
                set.add(temp);
                helper(j + 1, s, set, ans);
                set.remove(temp);
            }
        }
    }
}