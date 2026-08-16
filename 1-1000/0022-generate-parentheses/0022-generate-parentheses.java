class Solution {
    public List<String> ans = new ArrayList<>();
    public StringBuilder builder = new StringBuilder();

    public List<String> generateParenthesis(int n) {
        backTracking(n, 0, 0);
        return ans;    
    }

    public void backTracking(int n, int left, int right) {
        if (left == n && right == n) {
            ans.add(builder.toString());
            return;
        }
        if (left < n) {
            builder.append('(');
            backTracking(n, left + 1, right);
            builder.deleteCharAt(builder.length() - 1);
        }
        if (right < left) {
            builder.append(')');
            backTracking(n, left, right + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}