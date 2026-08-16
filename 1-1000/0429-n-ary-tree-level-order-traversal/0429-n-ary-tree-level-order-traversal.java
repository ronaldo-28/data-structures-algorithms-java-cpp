/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        helper(root, result, 0);
        return result;
    }
    
    public void helper(Node node, List<List<Integer>> result, int level){
        if(node == null) return;
        if(result.size() <= level) result.add(new ArrayList<Integer>());
        result.get(level).add(node.val);
        List<Node> childList = node.children;
        for(int i=0;i<childList.size();i++){
            Node childNode = childList.get(i);
            helper(childNode, result, level+1);
        }
    }
}