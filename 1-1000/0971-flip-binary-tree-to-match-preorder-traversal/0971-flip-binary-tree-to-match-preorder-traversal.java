class Solution {
    int idx=0;
    List<Integer> ans;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] arr) {
        ans=new ArrayList<>();
        if(sol(root,arr)) return ans;
        return Arrays.asList(-1);
    }
    public boolean sol(TreeNode root,int[] arr){
        if(root==null) return true;
        if(root.val!=arr[idx]) return false;
        idx++;
        if(root.left!=null && root.left.val!=arr[idx]){
            ans.add(root.val);
            return sol(root.right,arr) && sol(root.left,arr);
        }
        return sol(root.left,arr) && sol(root.right,arr);
    }
}