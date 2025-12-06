/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<Integer> result;
    Map<Integer,Integer> m;
    int len = 0;

    private int find(TreeNode root){
        if(root == null){
            return 0;
        }

        int left  = find(root.left);
        int right = find(root.right);

        int val = root.val + left + right;
        m.put(val,m.getOrDefault(val,0) + 1);
        int freq = m.get(val);
        // System.out.println(freq);
        if( freq > len ){
            len = freq;
            result = new ArrayList();
        }
        if(len == freq){
            result.add(val);
        }

        return val;
    }

    public int[] findFrequentTreeSum(TreeNode root) {
        m = new HashMap<>();
        find(root);
        // System.out.println(result.size());
        int[] arr = new int[result.size()];
        for(int i = 0; i < arr.length; ++i){
            arr[i] = result.get(i);
        }
        return arr;
    }
}